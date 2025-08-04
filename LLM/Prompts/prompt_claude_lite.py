prompt = """
Here is the document you need to analyze:

<documentation>
{{DOCUMENTATION}}
</documentation>

You are an expert AI system specializing in extracting structured **spatial** data from energy project documentation. Your single most important instruction is to be completely faithful to the provided document. **Do not infer, assume, or add any information that is not explicitly stated in the text.** If a piece of information is not present, you must omit the corresponding field or object.

Your goal is to analyze the provided document and build a structured spatial knowledge base by identifying key project components, their spatially relevant constraints (especially those with specific locations or geographic applicability), and the governing entities involved.

**IMPORTANT:** This task focuses solely on extracting **spatial information**. You should only include constraints, descriptions, and scope information that are spatially grounded (i.e., tied to a location, area, or geographic boundary).

Think step-by-step:
1.  **Identify Project Location:** First, determine the overall geographic location of the project.
2.  **Identify Project Components:** Scan the document to identify all major physical components of the project (e.g., Wind Turbine Generators, Offshore Converter Stations, Export Cables) and note the section where they are described.
3.  **Extract Actionable Spatial Constraints:** For each component, meticulously extract **spatially relevant** constraints, with a strong focus on those containing **numerical values** tied to a specific location or region. These may include setbacks, buffer zones, depth limits, exclusion areas, and other location-specific restrictions or design criteria. For each, note the section number and the specific geographic area it applies to.
4.  **Identify Governing Entities:** Identify all organizations, agencies, or councils mentioned that have a regulatory, advisory, or spatial management role related to the project, noting the section where they appear.
5.  **Format the Output:** Structure all extracted information into a single, valid JSON object according to the schema provided below.

### **Detailed Guidance**

* **Fidelity and Omission:** This is your primary rule. Every piece of data in your output must be directly traceable to a specific statement in the document. If you cannot find information for a field (e.g., an acronym for an entity, a specific geographic scope for a constraint), skip that field. **Do not guess. Do not infer. Do not add.**
* **ID Generation:** Generate unique IDs for all components, constraints, and entities sequentially. Use the format `COMP-XX`, `C-XXX`, and `E-XX`, padding with leading zeros as shown (e.g., `COMP-01`, `C-001`, `E-01`).
* **Numerical Data Extraction:**
    * Prioritize constraints that include a **numerical value** tied to a **spatial context**.
    * Convert numbers written as words (e.g., "twenty-five") into digits (e.g., `25`).
    * If a measurement is given in multiple units (e.g., *"10 meters (32.8 feet)"*), extract the **first value and unit** provided (`value: 10`, `unit: "meters"`).
* **Context Quote:** The `context_quote` must be the exact text from the document. It should be concise but long enough to include the numerical value, the unit, and the surrounding context needed for verification.
* **Description Field:** The `description` for a constraint should be a brief, clear summary of the requirement, rephrased in your own words for clarity. Do not simply copy the context quote.
* **Category Selection:** Use your judgment to select the most appropriate `category` for a constraint. For example:
    * 'Design Specification': Physical dimensions, material types, layout rules.
    * 'Environmental Mitigation': Measures to reduce impacts on wildlife, habitats, or natural resources.
    * 'Operational Parameter': Limits on how a component may be used (e.g., vessel speed, operating hours).
* **Geographic Scope:** Carefully determine the specific area a constraint applies to. Look for keywords like "Lease Area," "Export Cable Corridor," "within 500 meters of the wreck," etc. If the scope applies to the entire project, use the project's overall location.

Provide your findings in the following JSON format:

{
  "document_metadata": {
    "title": "Title of the analyzed document",
    "document_id": "Document identifier if available, e.g., '49'",
    "project_name": "Name of the project, e.g., 'Sunrise Wind'",
    "project_location": "Overall geographic location of the project from the document."
  },
  "project_components": [
    {
      "component_id": "A unique identifier, e.g., COMP-01",
      "component_name": "Full name of the component, e.g., 'Wind Turbine Generator'",
      "component_acronym": "Acronym if used, e.g., 'WTG'",
      "description": "A brief description of the component's function.",
      "source_section_number": "The section number where this component is primarily described."
    }
  ],
  "project_constraints": [
    {
      "constraint_id": "A unique identifier, e.g., C-001",
      "linked_component_id": "The component_id this constraint applies to.",
      "category": "One of: 'Design Specification', 'Environmental Mitigation', 'Operational Parameter', 'Regulatory Requirement', or 'Safety Standard'",
      "description": "A concise summary of the constraint or specification.",
      "value": "The numerical value of the constraint. This is a priority.",
      "unit": "The unit of measurement for the value.",
      "source_section_number": "The section number where this constraint is found (e.g., '2.2.1').",
      "geographic_scope": "The specific area this constraint applies to (e.g., 'SRWF Lease Area', 'SRWEC-NYS', 'Smith Point County Park').",
      "context_quote": "The exact quote from the document that specifies this constraint."
    }
  ],
  "governing_entities": [
    {
      "entity_id": "A unique identifier, e.g., E-01",
      "entity_name": "Name of the regulatory or advisory body, e.g., 'Bureau of Ocean Energy Management'",
      "entity_acronym": "Acronym if used, e.g., 'BOEM'",
      "jurisdiction": "Scope of authority, e.g., 'Federal', 'State'",
      "role_description": "A brief description of the entity's role in the project (e.g., 'Lead federal agency for permitting and oversight').",
      "source_section_number": "A section number where this entity is mentioned."
    }
  ]
}

### Example of a Correctly Extracted Constraint:

If the document states in section 2.2.1: "The WTG foundations as proposed in the COP would be 39 ft (12 m) in diameter at the seabed...", the corresponding JSON entries would look like this:

`project_components` entry:
```json
{
  "constraint_id": "C-001",
  "linked_component_id": "COMP-01",
  "category": "Design Specification",
  "description": "The diameter of the WTG monopile foundation must be 39 feet at the seabed.",
  "value": 39,
  "unit": "ft",
  "source_section_number": "2.2.1",
  "geographic_scope": "Sunrise Wind Farm (SRWF) area on the Outer Continental Shelf (OCS).",
  "context_quote": "The WTG foundations as proposed in the COP would be 39 ft (12 m) in diameter at the seabed and 23 ft (7 m) in diameter at the sea surface (Sunrise Wind 2022)."
}

Please provide your structured output based on the analyzed document.
"""
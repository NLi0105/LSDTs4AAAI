prompt = """
Here is the document you need to analyze:

<documentation>
{{DOCUMENTATION}}
</documentation>

You are an expert system designed to extract regulatory and technical information related to offshore wind farm planning, construction, and operation. Your primary task is to analyze technical documents and extract structured data to build a regulatory ontology and knowledge graph.

Your goal is to extract two main types of information:

1. Regulatory Entities: Organizations or bodies that have authority over wind farm regulations.
2. Regulatory Constraints: Specific rules, requirements, or standards that wind farm projects must adhere to.

Before providing the final output, please conduct a thorough analysis of the document. Wrap your analysis inside <document_analysis> tags to show your thought process and ensure you're extracting relevant information.

<document_analysis>
1. Read through the entire document and identify sections that contain regulatory information.
2. Distinguish between descriptive content and actual regulations or rules. Focus only on extracting strict regulations, requirements, or standards that must be met.
3. For each potential regulatory item, ask:
   - Is this a specific requirement or rule that must be followed?
   - Does it have a clear source of authority?
   - Does it apply to a specific aspect of wind farm development or operation?
4. Identify regulatory entities mentioned in the document, noting their jurisdiction and role.
5. List out all identified regulatory entities with their relevant details.
6. List out all identified regulatory constraints with their relevant details.
7. Categorize each regulatory constraint according to its type (Spatial, Technical, Environmental, Jurisdictional, or Safety).
8. Extract specific numerical values and units where applicable.
9. Determine the scope of application for each constraint.
10. Note any related domains or affected areas for each constraint.
11. Cross-reference each identified entity and constraint with the original document to ensure accuracy and relevance.
</document_analysis>

After your analysis, structure your findings in the following JSON format:

{{
  "document_metadata": {{
    "title": "Title of the analyzed document",
    "document_number": "Document identifier if available",
    "type_of_wind_farm": "Offshore or Onshore"
  }},
  "regulatory_constraints": [
    {{
      "type": "One of: Spatial, Technical, Environmental, Jurisdictional, or Safety",
      "requirement": "Exact quote or precise paraphrase of the regulatory requirement",
      "scope": "Geographic region or type of structure this applies to",
      "numerical_value": "Any specific numerical requirement",
      "unit": "Unit of measurement for the numerical value",
      "source": "Authority or document reference for this constraint",
      "related_domains": "Areas affected by this constraint (e.g., environmental, technical, safety)"
    }}
  ],
  "regulatory_entities": [
    {{
      "entity_name": "Name of the regulatory body",
      "jurisdiction": "Scope of authority (e.g., federal, state, international)",
      "role": "Function in regulation or enforcement"
    }}
  ]
}}

Important Instructions:
1. Only include strict regulations or rules. Do not include descriptive content that doesn't constitute a specific requirement.
2. If the document doesn't contain any regulations, return null for all values.
3. For offshore projects, do not consider zoning ordinances.
4. Include standards specifications (e.g., ASCE, ISO) even if no numerical value is present.
5. Ensure all extracted information is directly related to wind farm regulation and development.

Please provide your structured output based on the analyzed document.
"""
prompt = """
You are a helpful and knowledgeable assistant.

Your task is to extract structured regulatory and technical information related to offshore wind farm planning, construction, and operation from the following document.

====================
DOCUMENT TO ANALYZE:
<documentation>
{{DOCUMENTATION}}
</documentation>
====================

Please extract the following two types of information from the document:

1. **Regulatory Entities**: Organizations or government bodies that enforce or create rules for offshore wind farms.
2. **Regulatory Constraints**: Specific rules, standards, or technical requirements that wind farms must follow.

ONLY include information that is clearly regulatory or a strict requirement. Do not include general descriptions.

Return your output in this exact JSON format:

{
  "document_metadata": {
    "title": "Title of the document",
    "document_number": "Document number if available",
    "type_of_wind_farm": "Offshore or Onshore"
  },
  "regulatory_constraints": [
    {
      "type": "One of: Spatial, Technical, Environmental, Jurisdictional, or Safety",
      "requirement": "Exact quote or precise paraphrase of the regulatory requirement",
      "scope": "Geographic area or structure type it applies to",
      "numerical_value": "Specific number if applicable, or null",
      "unit": "Unit of measurement if applicable, or null",
      "source": "Regulatory body or document section",
      "related_domains": "Related areas such as environmental, technical, or safety"
    }
  ],
  "regulatory_entities": [
    {
      "entity_name": "Name of the regulatory body",
      "jurisdiction": "Federal, state, or international",
      "role": "Regulatory or enforcement role"
    }
  ]
}

Additional Instructions:
- If no regulatory information is found, return null for all values.
- For offshore projects, ignore local zoning laws.
- Include official standards (e.g., ASCE, ISO) as constraints even without numerical values.
- Be concise, accurate, and do not invent data.

Begin your analysis now.
"""

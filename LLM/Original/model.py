import time
import anthropic
from openai import OpenAI


SECRET_FILE = 'secrets.txt'
with open('secrets.txt') as f:
    lines = f.readlines()
    for line in lines:
        if line.split(',')[0].strip() == "openai_key":
            openai_key = line.split(',')[1].strip()
        elif line.split(',')[0].strip() == "claude_key":
            anthropic_key = line.split(',')[1].strip()

openai_client = OpenAI(api_key=openai_key)
claude_client = anthropic.Anthropic(api_key=anthropic_key)


def call_claude_sonet(message):
    """Call the Claude 3 Sonet model for text information and return the response."""
    try:
        system_prompt = "You are a rational assistant that carefully answer the question."
        message = claude_client.messages.create(
            model="claude-3-sonnet-20240229",
            max_tokens=4000,
            temperature=1,
            system=system_prompt,
            messages=[{"role": "user", "content": message}]
            )
        return message.content[0].text
    except Exception as e:
        print(f"Error calling Claude: {e}")
        return None


def call_claude_haiku(message):
    """Call the Claude 3 Haiku model for text information and return the response."""
    try:
        system_prompt = "You are a rational assistant that carefully answer the question."
        message = claude_client.messages.create(
            model="claude-3-haiku-20240307",
            max_tokens=4000,
            temperature=1,
            system=system_prompt,
            messages=[{"role": "user", "content": message}]
            )
        return message.content[0].text
    except Exception as e:
        print(f"Error calling Claude: {e}")
        return None


def call_gpt4o_mini(message):
    """Call the GPT model for text information and return the response."""
    try:
        response = openai_client.chat.completions.create(
            model = "gpt-4o-mini",
            messages=[{"role": "user", 
                       "content": message}],
            temperature=0.0,
            max_tokens=4000
        )
        return response.choices[0].message.content
    except Exception as e:
        print(f"Error calling GPT: {e}")
        return None
    
    
def call_gpt4o(message):
    """Call the GPT model for text information and return the response."""
    try:
        response = openai_client.chat.completions.create(
            model = "gpt-4o",
            messages=[{"role": "user", 
                       "content": message}],
            temperature=0.0,
            max_tokens=4000
        )
        return response.choices[0].message.content
    except Exception as e:
        print(f"Error calling GPT: {e}")
        return None
    
    
def call_gpt35(message):
    """Call the GPT model for text information and return the response."""
    try:
        response = openai_client.chat.completions.create(
            model = "gpt-3.5-turbo",
            messages=[{"role": "user", 
                       "content": message}],
            temperature=0.0,
            max_tokens=2000
        )
        return response.choices[0].message.content
    except Exception as e:
        print(f"Error calling GPT: {e}")
        return None
    

if __name__ == '__main__':
    message = "how are you?"
    call_gpt4o_mini()
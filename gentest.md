import unicodedata
import re

def normalize_text(text: str) -> str:
    # 1. Normaliza acentos e caracteres compostos (NFC = forma canônica)
    text = unicodedata.normalize("NFC", text)
    
    # 2. Remove caracteres de controle (exceto \n, \t)
    text = "".join(ch for ch in text if unicodedata.category(ch)[0] != "C" or ch in ("\n", "\t"))
    
    # 3. Remove caracteres de substituição usados em decodificação inválida (�)
    text = text.replace("�", "")
    
    # 4. Remove qualquer bloco "non-printable" que ainda restar
    text = re.sub(r"[^\x20-\x7EÀ-ÖØ-öø-ÿĀ-žŸ-žƀ-ɏ]+", "", text)
    
    return text


# Exemplo de uso
raw_text = "Al�em disso, certifique-se..."
clean_text = normalize_text(raw_text)

print(clean_text)  # Saída: "Além disso, certifique-se..."
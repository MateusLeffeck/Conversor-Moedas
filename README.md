# 💱 Conversor de Moedas (Java + API)

Projeto simples em Java que consome uma API de câmbio para converter valores entre moedas.

---

## 📌 Funcionalidades

- Seleção de moeda de origem e destino
- Conversão de valores em tempo real
- Interface via terminal (CLI)
- Suporte a diversas moedas (USD, BRL, EUR, etc)
- Uso seguro de chave de API com `config.properties`

---

## 🛠️ Tecnologias usadas

- Java 8+
- API [ExchangeRate-API](https://www.exchangerate-api.com/)
- Biblioteca JSON: [Gson](https://github.com/google/gson)

---

## 🧪 Como rodar

1. Clone o repositório:

```bash
git clone https://github.com/MateusLeffeck/Conversor-Moedas.git
cd Conversor-Moedas
```
---

2. Crie um arquivo config.properties com sua chave de API:
   API_KEY=chave

---

3. Compile e execute:
```bash
javac -cp .;gson-2.10.1.jar Main.java
java -cp .;gson-2.10.1.jar Main
```


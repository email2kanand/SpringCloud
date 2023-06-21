Initial

- http://localhost:8765/CURRENCY-EXCHANGE-SERVICE/currency-exchange/from/USD/to/INR
- http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion-feign/from/USD/to/INR/quantity/10

lowercase:
- http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion-service/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/USD/to/INR/quantity/10

- http://localhost:8765/identity-service/auth/validate?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaW50dSIsImlhdCI6MTY4NzI4OTkwMiwiZXhwIjoxNjg3MjkxNzAyfQ.IQ-bEzdAOkVCZwWBOLG3Qfc2aDRhzIsaQBaZFaATz5Y



Custom Routes

- http://localhost:8765/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10


VAULT
----------
 Vault is a tool for securely accessing secrets. A secret can be anything that needs to be provided 
 controlled access. For e.g., API keys, passwords, database credentials, certificates, and more.  
 All the secure details are stored in Vault to avoid hard coding of them anywhere. All applications
  access Vault during initialization and while processing. Since Vault is the heart of the System,
  it should support highly availability and Fault Tolerance. 



# ETL para arquivos CSV em S3 buckets
### Propósito do Projeto
O projeto foi desenvolvido para implementar o processo de ETL (Extract, Transform, Load) em buckets da AWS, facilitando a transferência de arquivos CSV contendo dados de capturas de uso de máquinas do bucket raw (dados brutos) para o bucket trusted (dados confiáveis). Durante essa transferência, os arquivos são transformados e consolidados em um único arquivo armazenado no bucket trusted, garantindo a unicidade na identificação de cada dado. 

#!/bin/bash

# Declarar variables
docker_base_dir="$HOME/dev/docker"
mysql_data_dir="$docker_base_dir/mysql-db/data"
mysql_logs_dir="$docker_base_dir/mysql-db/logs"
oracledb_data_dir="$docker_base_dir/oracle-db/data"
oracledb_logs_dir="$docker_base_dir/oracle-db/logs"
postgredb_data_dir="$docker_base_dir/postgre-db/data"
postgredb_logs_dir="$docker_base_dir/postgre-db/logs"
db_root_psswd="${DB_ROOT_PSSWD:-my_p4ssw0rd}" # TODO: mover esto a env por motivos de seguridad

# Instalar docker (-y evitar confirmaciones manuales)
sudo apt update
sudo apt install docker.io -y

# Iniciar docker
sudo service docker start

# Habilitar inicio automático Docker junto a WSL2
sudo systemctl enable docker 2>/dev/null || echo "systemctl no está disponible en este entorno"

# Iniciar Docker si no está corriendo ya
if ! sudo docker info > /dev/null 2>&1; then
    echo "Iniciando Docker..."
    sudo service docker start
fi

# Crear directorios de persistencia de datos (-p Crea directorios intermedios si no existen)
mkdir -p "$mysql_data_dir" \
    "$mysql_logs_dir" \
    "$oracledb_data_dir" \
    "$oracledb_logs_dir"

# Crear y ejecutar contenedor de MySQL
# --name <CONTAINER_NAME> Nombre del contenedor creado
# -e <ROOT_PSSWD> Establece la contraseña del usuario root
# -v <HOST_DIR:CONTAINER_DIR> Monta directorios de persistencia en el contenedor
# -p <HOST_PORT:CONTAINER_PORT> Vincula el puerto del host al puerto del contenedor
# -d <IMAGE_NAME:VERSION> Imagen y versión con la que vamos a crear el contenedor
sudo docker run --name mysql-db \
                -e MYSQL_ROOT_PASSWORD="$db_root_psswd" \
                -v "$mysql_data_dir":/var/lib/mysql \
                -v "$mysql_logs_dir":/var/log/mysql \
                -p 3306:3306 \
                -d mysql:latest

# iniciar sesión con nuestra cuenta de oracle
sudo docker login container-registry.oracle.com

# descargar la imagen de oracle database
sudo docker pull container-registry.oracle.com/database/free:latest

# Crear y ejecutar contenedor de PostgreSQL
sudo docker run --name postgre-db \
    -e POSTGRES_USER=root \
    -e POSTGRES_PASSWORD="$db_root_psswd" \
    -e POSTGRES_DB=postgre_local_db \
    -v "$postgredb_data_dir":/var/lib/postgresql/data \
    -v "$postgredb_logs_dir":/var/lib/postgresql/logs \
    -p 5432:5432 \
    -d postgres:latest

# Crear y ejecutar contenedor de Oracle Database
sudo docker run --name oracle-db \
                -e ORACLE_SID=ORCLCDB \
                -e ORACLE_PDB=ORCLPDB1 \
                -e ORACLE_EDITION=free \
                -e ORACLE_PWD="$db_root_psswd" \
                -v "$oracledb_data_dir":/opt/oracle/oradata \
                -v "$oracledb_logs_dir":/opt/oracle/diag \
                -p 1521:1521 -p 5500:5500 \
                -d container-registry.oracle.com/database/free:latest

# Verificar ejecución de contenedores
sudo docker ps # Muestra la lista de contenedores activos

# ---------------------------------------------------------

# Conectarse a MySQL desde WSL2
sudo apt install mysql-client -y
# -h <HOST> | -P <PORT> | -u <USER_NAME> | -p<USER_PASSWORD>
mysql -h 127.0.0.1 \
    -P 3306 \
    -u root \
    -p"$db_root_psswd"

# Conectarme a MySQL directamente desde el docker
sudo docker exec -it mysql-db bash
mysql -u root -p # Se nos pedirá la contraseña del usuario root que establecimos anteriormente

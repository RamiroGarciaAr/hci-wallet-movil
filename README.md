🚀 Requisitos Previos

Antes de empezar, asegúrate de contar con los siguientes elementos:

- [Android Studio](https://developer.android.com/studio) (última versión recomendada).
- Java Development Kit (JDK) versión 11 o superior.
- Gradle (administrado automáticamente por Android Studio).
- Un dispositivo Android físico o un emulador configurado.

🛠️ Pasos para Configurar el Proyecto

1️⃣ Clonar o Descargar el Proyecto
Clona este repositorio o descárgalo manualmente como archivo ZIP:

git clone https://github.com/usuario/nombre-repositorio.git

1. Abre Android Studio.
2. Ve a File > Open y selecciona la carpeta del proyecto.
3. Android Studio descargará las dependencias automáticamente.

2️⃣ Configurar el SDK

1. Abre File > Project Structure > SDK Location.
2. Verifica que la ubicación del SDK esté configurada correctamente.
3. Si faltan versiones del SDK, descárgalas desde Tools > SDK Manager.

3️⃣ Configurar un Emulador

1. Abre Tools > Device Manager.
2. Crea un emulador (recomendado: Pixel 5 con Android 12 o superior).
3. Haz clic en Run para iniciar el emulador.

▶️ Construir y Ejecutar la Aplicación

Construir el Proyecto

- Haz clic en Build > Make Project o utiliza el botón de construcción en la barra de herramientas.

Ejecutar la Aplicación

1. Conecta un dispositivo físico con Depuración USB habilitada o inicia el emulador.
2. Haz clic en Run > Run 'app' o en el botón de ejecución (triángulo verde).
3. La aplicación se instalará automáticamente y se abrirá en el dispositivo o emulador.

🛠️ Solución de Problemas Comunes

⚠️ Error: "Failed to sync Gradle project"

- Ve a File > Sync Project with Gradle Files.
- Asegúrate de tener una conexión a internet activa.

⚠️ El emulador no inicia

- Verifica la configuración del emulador.
- Aumenta la RAM asignada en las configuraciones del emulador.

⚠️ Problemas con Gradle

1. Revisa conflictos en las dependencias dentro de build.gradle.
2. Limpia y reconstruye el proyecto con el siguiente comando:

./gradlew clean build

⚠️ Errores de permisos en dispositivos físicos

1. Habilita Depuración USB:
   - Ve a Configuración > Acerca del Teléfono > Número de compilación.
   - Toca 7 veces para activar las Opciones de Desarrollador.
2. Activa Depuración USB en las Opciones de Desarrollador.

✅ Probar la Aplicación

1. Abre la aplicación en tu dispositivo o emulador.
2. Navega por las pantallas principales y verifica las funcionalidades clave:
   - Inicio de sesión.
   - Registro de usuario.
   - Flujos de navegación.

🔑 Claves de API

Si el proyecto requiere claves de API o configuraciones específicas, agrégalas en un archivo local.properties o .env.

📦 Generar un APK o AAB

Sigue estos pasos para generar un archivo listo para distribución:

1. Ve a Build > Build Bundle(s) / APK(s) > Build APK(s).
2. El archivo generado estará en la ruta:

app/build/outputs/apk/

🤝 Contribuir

¡Las contribuciones son bienvenidas! Para contribuir:

1. Haz un fork del repositorio.
2. Crea una rama nueva para tus cambios:

git checkout -b nombre-de-tu-rama

3. Realiza tus cambios y súbelos:

git add .
git commit -m "Descripción de los cambios"
git push origin nombre-de-tu-rama

4. Abre un Pull Request en este repositorio.

📄 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.

¡Gracias por contribuir y ser parte de este proyecto! 🚀

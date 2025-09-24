**BRAYAN DENILSON SACALXOT ELIAS 7590-24-25740**

**Descripción breve:**
Aplicación simple para gestionar notas personales usando Jetpack Compose y la arquitectura MVVM. Permite agregar y eliminar notas; el estado se mantiene en un `ViewModel` y se observa desde Composables mediante `collectAsStateWithLifecycle()`.

**Estructura del proyecto**
- `model/Note.kt` - Modelo de datos.
- `vm/NoteViewModel.kt` - Lógica de negocio / estado (MVVM).
- `MainActivity.kt` - Activity que inyecta el ViewModel.
- `MainScreen.kt` - UI con Composables para mostrar/añadir/eliminar notas.
- `docs/errores-comunes.md` - Errores encontrados y sus soluciones.

**Cómo apliqué MVVM**
- Model: `Note` es el modelo inmutable (data class).
- ViewModel:`NoteViewModel` contiene la lista de notas como `StateFlow<List<Note>>`, expone funciones `addNote()` y `removeNote()` y mantiene la generación de IDs.
- View (UI): `MainScreen` observa las notas con `collectAsStateWithLifecycle()` y llama a las funciones del ViewModel para acciones (agregar/eliminar). Esto respeta separación de responsabilidades y permite que el estado sobreviva a rotaciones.

**Requisitos para compilar**
- Android Studio (última versión compatible con Compose).
- compileSdk 34, Kotlin compatible.
- Dependencias: Compose BOM, material3, lifecycle-viewmodel-ktx, lifecycle-runtime-compose.

**Capturas**

- Pantalla con la lista vacía.
  

  ![Imagen de WhatsApp 2025-09-23 a las 20 20 37_04b8d7a3](https://github.com/user-attachments/assets/20dacfa4-e586-4a69-8a35-c4fd19228f96)

- Agregando notas.

  
![Imagen de WhatsApp 2025-09-23 a las 20 20 37_aef53ea1](https://github.com/user-attachments/assets/e96da5fb-d8e9-49ef-bae3-99e4f5447df5)

  
- Eliminando notas.

  
![Imagen de WhatsApp 2025-09-23 a las 20 20 37_4e24c426](https://github.com/user-attachments/assets/89227be3-5b82-42a3-8c44-31cdf617347c)

  

**Errores comunes encontrados y su solución**

Error 1 — La app pierde las notas al rotar la pantalla
Síntoma: Al rotar, la lista de notas se vacía.  
Causa: Estado almacenado en variables locales de Composable en lugar del ViewModel.  
Solución: Mantener la fuente de la verdad en `NoteViewModel` usando `StateFlow`/`LiveData`. En la UI usar `collectAsStateWithLifecycle()` para observar `vm.notes`. Ejemplo: `val notes by vm.notes.collectAsStateWithLifecycle()`.

Error 2 — Agregar nota vacía o con espacios
Síntoma: Se agregan notas con texto vacío o solo espacios.  
Causa: No se valida `trim()` antes de crear la nota.  
Solución:En `NoteViewModel.addNote()` hacer `val t = text.trim(); if (t.isEmpty()) return` y solo entonces crear la nota.

Error 3 — Duplicado de IDs o IDs incorrectos
Síntoma: Ítems con key duplicado en LazyColumn, resultado en render extraño.  
Causa:Uso de índices como key o reinicio del contador de IDs.  
Solución: Usar un generador de IDs robusto (`AtomicLong`) en el ViewModel: `private val seq = AtomicLong(0)`. Generar ids con `seq.incrementAndGet()`.

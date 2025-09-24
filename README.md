

# Errores comunes encontrados y su solución

## Error 1 — La app pierde las notas al rotar la pantalla
**Síntoma:** Al rotar, la lista de notas se vacía.  
**Causa:** Estado almacenado en variables locales de Composable en lugar del ViewModel.  
**Solución:** Mantener la fuente de la verdad en `NoteViewModel` usando `StateFlow`/`LiveData`. En la UI usar `collectAsStateWithLifecycle()` para observar `vm.notes`. Ejemplo: `val notes by vm.notes.collectAsStateWithLifecycle()`.

## Error 2 — Agregar nota vacía o con espacios
**Síntoma:** Se agregan notas con texto vacío o solo espacios.  
**Causa:** No se valida `trim()` antes de crear la nota.  
**Solución:** En `NoteViewModel.addNote()` hacer `val t = text.trim(); if (t.isEmpty()) return` y solo entonces crear la nota.

## Error 3 — Duplicado de IDs o IDs incorrectos
**Síntoma:** Ítems con key duplicado en LazyColumn, resultado en render extraño.  
**Causa:** Uso de índices como key o reinicio del contador de IDs.  
**Solución:** Usar un generador de IDs robusto (`AtomicLong`) en el ViewModel: `private val seq = AtomicLong(0)`. Generar ids con `seq.incrementAndGet()`.

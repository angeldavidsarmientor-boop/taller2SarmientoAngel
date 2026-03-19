¿Qué problema resuelve el ViewModel?:

Es cuando se rota el teléfono, la Activity se destruye y pierdes todos los datos. El ViewModel los guarda y los mantiene vivos aunque la pantalla rote.

¿Por qué LiveData es "lifecycle-aware" y qué beneficio trae?:

Es porque sabe en qué estado está la app, es decir si esta activa, pausada o destruida y solo notifica cambios cuando la pantalla está visible. Así evita problemas por actualizar una pantalla que ya no existe.

Explica con tus propias palabras el flujo de datos en MVVM:

Es la Vista que le pide datos al ViewModel, el ViewModel se los pide al Repository y el Repository los busca y los devuelve. El ViewModel los publica en LiveData y la Vista se actualiza automáticamente.

¿Qué ventaja tiene usar Fragments vs múltiples Activities?:

Los Fragments son más ligeros y pueden compartir el mismo ViewModel dentro de una Activity. Con múltiples Activities tendrías que pasar datos entre ellas con Intents, lo cual es más complicado y lento.

¿Cómo ayuda el Repository Pattern a la arquitectura?:

El ViewModel no sabe de dónde vienen los datos. El Repository se encarga de eso. Así si se cambia la fuente de datos, solo se seleccionael Repository y no el resto de la app.

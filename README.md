En este taller se implementó un módulo completo de gestión de tareas dentro de la aplicación. Se desarrolló la navegación entre pantallas utilizando el Navigation Component, permitiendo pasar de la lista de tareas a la pantalla de creación de una nueva tarea de forma fluida. Además, se creó la funcionalidad para registrar tareas con un título, una descripción y la opción de activar un recordatorio.

Las tareas se visualizan en una lista mediante el uso de RecyclerView, mientras que la gestión de los datos se realiza a través de un ViewModel, lo que permite mantener la información durante el ciclo de vida de la aplicación. También se integró un BroadcastReceiver para manejar eventos en segundo plano.

Para los recordatorios, se utilizó la opción de notificación local. Cuando el usuario activa el recordatorio al momento de crear una tarea, se programa una alarma utilizando AlarmManager. Una vez se cumple el tiempo establecido, el sistema ejecuta el BroadcastReceiver, el cual se encarga de mostrar una notificación en el dispositivo con la información de la tarea creada.

Es importante tener en cuenta que el funcionamiento de las notificaciones puede depender de los permisos del sistema, especialmente en versiones recientes de Android, así como de posibles restricciones de batería del dispositivo.

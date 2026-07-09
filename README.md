# Sistema-Reserva-Clases
Proyecto Semestral - Desarrollo Orientado a Objetos - Ingeniería Civil Informática

Desarrolladores: Martin Ignacio Bastias Neira, Daniel Esteban Ortiz Estrada y Benjamin Alonso Silva Sepúlveda.

Patrones Implementados:

State: "Aplicamos el patrón State para eliminar la lógica condicional compleja (bloques if/else o switch) dentro de la clase Reserva al intentar modificarla o cancelarla. Esto nos permitió respetar el Principio de Responsabilidad Única (SRP), ya que cada estado gestiona sus propias reglas de negocio; y el Principio Abierto/Cerrado (OCP), porque si en el futuro queremos añadir un estado 'En Curso', solo creamos una nueva clase sin tener que modificar el código de la clase Reserva".


Análisis Crítico de Patrones Propuestos
1. Strategy (Filtros de Calendario) - Excelente elección El enunciado exige explícitamente mantener un calendario con "vistas filtradas para cada tutor o estudiante". El patrón Strategy encaja a la perfección.
   Implementación: Ya tienes la interfaz FiltrarStrategy. Puedes crear clases concretas como FiltroPorMateria y FiltroPorTutor.
   Para usar múltiples filtros a la vez: En lugar de crear combinaciones fijas (lo cual rompe la escalabilidad), puedes aplicar un enfoque donde tu método de filtrado reciba una lista de FiltrarStrategy. La lógica iterará sobre las reservas y solo devolverá aquellas que cumplan con todos los strategies proporcionados.
2. Facade (Fachada para la Interfaz) - Muy pertinente La rúbrica penaliza severamente si no hay una "clara separación de responsabilidades". La GUI (SistemaTutoriasGUI) jamás debe instanciar reservas ni validar conflictos de horario.
   Implementación: Tu clase Administrador puede actuar perfectamente como el Facade. Este patrón expone un método simple hacia la GUI, por ejemplo: administrador.agendarClase(matricula, idTutor, materia, horario). Por debajo, el Facade se encarga de llamar a GestorPerfil para buscar al estudiante y al tutor, y luego a GestorReservas para verificar que no haya choques de horario o de cupos (lanzando tu CupoExcedidoException si es necesario).
3. State (Estado de la Reserva) - No es forzado si se aplica correctamente El patrón State es forzado solo cuando se usa para reemplazar un simple String o Enum que no hace nada más que mostrar un texto. Para que el patrón esté bien justificado y sume puntos en complejidad, el estado debe cambiar el comportamiento de la reserva.
   Implementación: Si una reserva está Pendiente, sus métodos modificar() o cancelar() ejecutan la acción. Si el estado cambia a Cancelada o Completada, llamar a modificar() debe lanzar una excepción (ej. IllegalStateException), bloqueando cualquier intento de alterar el historial.
   Sobre la "basura" de las clases canceladas: En sistemas reales de software, los registros casi nunca se borran permanentemente (Hard Delete). Se ocultan de las vistas principales mediante un "Soft Delete" o cambio de estado. Mantener el registro de clases canceladas es una excelente práctica para temas de auditoría o métricas del administrador.
4. Observer y Factory Method (Tus dudas)
   Observer (Muy recomendado): Es ideal para comunicar la capa lógica con la GUI sin acoplarlas. Cuando el GestorReservas concreta una nueva reserva, notifica a sus observadores (en este caso, el panel del calendario en SistemaTutoriasGUI) para que actualice la vista automáticamente, sin necesidad de que el usuario presione un botón de "Actualizar Calendario".


"Durante el desarrollo, identificamos una ambigüedad en el dominio: la diferencia entre una 'Clase' (el evento del tutor) y una 'Reserva' (el cupo del estudiante). Por restricciones de tiempo, optamos por un modelo donde la Reserva actúa como el ticket individual, manejando las cancelaciones masivas de clases iterando sobre las reservas afectadas. Como propuesta de mejora arquitectónica para una futura iteración, sugerimos separar la entidad 'Clase' de la entidad 'Reserva' para que ambas posean sus propios ciclos de vida y estados independientes."

Para la búsqueda y vistas filtradas del calendario, combinamos el Patrón Strategy con el Patrón Composite. En lugar de alterar la clase base o crear una infinidad de estrategias anidadas para cada combinación de la interfaz gráfica, desarrollamos un FiltroCompuesto que actúa como contenedor dinámico de estrategias simples. Esto nos permitió respetar estrictamente los principios SOLID, manteniendo nuestras clases cerradas a la modificación pero infinitamente abiertas a nuevos filtros."


Informe de Arquitectura y Patrones de Diseño Proyecto: Sistema de Reserva de Clases Particulares
1. Resumen de Evolución del Diseño El sistema fue sometido a una profunda refactorización arquitectónica para aislar la capa de dominio de la capa de presentación (GUI), basándonos en los principios SOLID y las directrices GRASP. Se eliminó la dependencia bidireccional y se erradicó el antipatrón "God Object", delegando la responsabilidad de la gestión de memoria a repositorios especializados (Gestores), logrando un alto grado de cohesión y un bajo acoplamiento estructural. Se implementó una lógica de validación transaccional (Fail-Fast) para evitar estados corruptos en memoria.
2. Implementación de Patrones de Diseño
   Facade + Singleton (Control Centralizado): La clase Sistema actúa como el único punto de entrada (Facade) para la interfaz gráfica. Centraliza la orquestación de operaciones complejas que cruzan múltiples dominios (ej. al eliminar un tutor, se comunican el GestorTutores y el GestorReservas). Su instanciación está bloqueada (private Sistema()) garantizando una única fuente de verdad en memoria mediante el patrón Singleton.
   State Pattern (Ciclo de Vida Transaccional): Para manejar la volatilidad funcional de una clase particular, se delegó el comportamiento de la clase Reserva a la interfaz EstadoReserva. Los estados concretos (EstadoPendiente, EstadoCompletada, EstadoCancelada) dictan en tiempo de ejecución si una reserva puede modificarse, ocupar cupos físicos o cancelarse, erradicando el uso masivo de sentencias if-else en la lógica de negocio.
   Strategy + Composite (Búsqueda Dinámica): El motor de búsqueda de tutores evita la sobrecarga de consultas SQL-like en código duro mediante el patrón Strategy (FiltrarStrategy). Para soportar búsquedas multicriterio (ej. Nombre + Materia simultáneamente), se incorporó el patrón Composite a través de FiltroCompuesto, permitiendo apilar dinámicamente múltiples estrategias de filtrado sin alterar el núcleo del gestor.
3. Coherencia con la Rúbrica El diseño actual cumple estrictamente con el principio de Responsabilidad Única (SRP), separando las vistas de la lógica de dominio. La inmutabilidad de colecciones (Collections.unmodifiableList()) y el uso avanzado de Java Streams garantizan la integridad referencial y algorítmica exigida en los requerimientos del proyecto.



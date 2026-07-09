Número de Grupo y Nombres
   Desarrolladores:

Martín Ignacio Bastías Neira

Daniel Esteban Ortiz Estrada

Benjamín Alonso Silva Sepúlveda

2. Enunciado del Proyecto
   El presente proyecto consiste en el diseño e implementación de un Sistema de Reserva de Clases enfocado en la gestión de tutorías académicas. La plataforma permite a los estudiantes buscar tutores, revisar sus disponibilidades horarias, materias impartidas y tarifas, para finalmente concretar reservas de clases.

El sistema garantiza la integridad de las reglas de negocio a través de una validación estricta, previniendo topes de horario, controlando los cupos máximos por clase y gestionando el ciclo de vida transaccional de cada reserva.

3. Diagrama de Casos de Uso
   A continuación, se presentan los actores principales y sus interacciones con los casos de uso definidos para el sistema.

4. Interfaz Gráfica
   Captura de la interfaz desarrollada para la interacción del usuario con la lógica del sistema, priorizando una clara separación de responsabilidades entre la presentación y el dominio.

5. Diagrama de Clases UML
   El siguiente diagrama refleja la arquitectura del software, detallando la jerarquía, relaciones y componentes de la capa lógica.

6. Patrones de Diseño Implementados y Justificación
   Para garantizar que el software cumpla estrictamente con los principios SOLID y las directrices GRASP, se implementaron los siguientes patrones de diseño:

6.1. State (Estado)
Justificación: Aplicamos el patrón State para eliminar la lógica condicional compleja (bloques if/else o switch) dentro de la clase Reserva al intentar modificarla o cancelarla. Esto nos permitió respetar el Principio de Responsabilidad Única (SRP), ya que cada estado gestiona sus propias reglas de negocio; y el Principio Abierto/Cerrado (OCP), porque si en el futuro queremos añadir un nuevo estado, solo creamos una nueva clase sin tener que modificar el código original de la reserva.

Clases Involucradas: EstadoReserva (Interfaz), EstadoPendiente, EstadoCompletada, EstadoCancelada, Reserva.

6.2. Strategy y Composite
Justificación: Para la búsqueda y las vistas filtradas del calendario, combinamos el Patrón Strategy con el Patrón Composite para evitar la sobrecarga de consultas estáticas en el código. En lugar de alterar la clase base o crear una infinidad de estrategias anidadas para cada combinación de la interfaz gráfica, desarrollamos un FiltroCompuesto que actúa como contenedor dinámico de estrategias simples. Esto nos permitió mantener nuestras clases cerradas a la modificación pero infinitamente abiertas a nuevos filtros.

Clases Involucradas: FiltrarStrategy (Interfaz), FiltroPorNombre, FiltroTutorPorMateria, FiltroCompuesto.

6.3. Facade (Fachada) y Singleton
Justificación: La clase Sistema actúa como el único punto de entrada (Facade) para la interfaz gráfica, orquestando las operaciones complejas que cruzan múltiples dominios (por ejemplo, al eliminar un tutor, se comunican el GestorTutores y el GestorReservas). Su instanciación está bloqueada mediante el patrón Singleton, garantizando un control centralizado y una única fuente de verdad en memoria.

Clases Involucradas: Sistema, GestorTutores, GestorEstudiantes, GestorReservas.

7. Decisiones Importantes del Proyecto
   El sistema fue sometido a una profunda refactorización arquitectónica para aislar la capa de dominio de la capa de presentación. A lo largo del ciclo de desarrollo, tomamos las siguientes decisiones clave:

Eliminación del antipatrón "God Object": Se eliminó la dependencia bidireccional y se delegó la responsabilidad de la gestión de memoria a repositorios especializados (Gestores). Esto nos permitió lograr un alto grado de cohesión y un bajo acoplamiento estructural.

Validación Transaccional (Fail-Fast): Implementamos una lógica de validación preventiva para evitar estados corruptos en memoria. Las operaciones fallan rápidamente lanzando excepciones específicas antes de alterar los objetos.

Inmutabilidad: Decidimos utilizar Collections.unmodifiableList() y el uso avanzado de Java Streams en las consultas para garantizar la integridad referencial y proteger las colecciones internas de modificaciones no deseadas desde el exterior.

Manejo de registros (Soft Delete): Optamos por no borrar permanentemente los registros de las clases canceladas. Mantener el registro mediante un cambio de estado es una práctica que adoptamos para mantener la trazabilidad de los datos.

8. Problemas Identificados y Autocrítica
   Durante el desarrollo, identificamos una ambigüedad en el dominio: la diferencia conceptual entre una "Clase" (el evento general del tutor) y una "Reserva" (el cupo individual del estudiante). Por restricciones de tiempo para esta entrega, optamos por un modelo donde la Reserva actúa directamente como el ticket individual, manejando las cancelaciones masivas iterando sobre las reservas afectadas.

Como propuesta de mejora arquitectónica para una futura iteración, sugerimos separar la entidad "Clase" de la entidad "Reserva" para que ambas posean sus propios ciclos de vida y estados independientes.

Finalmente, a modo de autocrítica respecto a la gestión del equipo, en las etapas iniciales existió un desajuste entre los diagramas UML propuestos y el código que se estaba implementando. Esto generó cuellos de botella que nos forzaron a realizar refactorizaciones estructurales avanzadas. Este proceso nos dejó como lección la importancia fundamental de consolidar y respetar el diseño de la arquitectura antes de comenzar la fase de codificación.
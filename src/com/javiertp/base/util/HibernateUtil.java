package com.javiertp.base.util;

import com.javiertp.base.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

  public static SessionFactory sessionFactory;
  public static Session session;
	
  /**
   * Crea la factoria de sesiones
   */
  public static void buildSessionFactory() {

    Configuration configuration = new Configuration();
    // La llamada al método configure() carga los parámetros del fichero hibernate.cfg.xml
    configuration.configure();
    
    // Se registran las clases que hay que mapear con cada tabla de la base de datos
    configuration.addAnnotatedClass(Evento.class);
    configuration.addAnnotatedClass(Inscripcion.class);
    configuration.addAnnotatedClass(Organizador.class);
    configuration.addAnnotatedClass(Usuario.class);
    configuration.addAnnotatedClass(Valoracion.class);

    //Se crea una SessionFactory a partir del objeto Configuration
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
      configuration.getProperties()).build();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  }
	
  /**
   * Abre una nueva sesión
   */
  public static void openSession() {
    session = sessionFactory.openSession();
  }
	
  /**
   * Devuelve la sesión actual
   * @return
   */
  public static Session getCurrentSession() {
	
    if ((session == null) || (!session.isOpen()))
      try {
        openSession();
      } catch (NullPointerException e) {
        System.out.println("No estás conectado a la BBDD");
      }

    return session;
  }
	
  /**
   * Cierra Hibernate
   */
  public static void closeSessionFactory() {
	
    if (session != null)
      session.close();
		
    if (sessionFactory != null)
      sessionFactory.close();
  }
}
package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public abstract class ImpJpaDAO<T, I extends Serializable> implements DAO<T, I> {
  //unidade de persistencia definia no persistence.xml
  private static final String UNIT_NAME = "entidades";
  private EntityManagerFactory emf = null;
  private EntityManager em = null;

  @Override
  public T save(T entity) {
    T saved = null;

    getEntityManager().getTransaction().begin();
    saved = getEntityManager().merge(entity);
    getEntityManager().getTransaction().commit();

    return saved;
  }

  @Override
  public void remove(T entity) {
    getEntityManager().getTransaction().begin();
    getEntityManager().remove(entity);
    getEntityManager().getTransaction().commit();
  }

  @Override
  public T getById(Class<T> classe, I id) {
    try{
      return getEntityManager().find(classe, id);
    }catch(NoResultException e){
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> getAll(Class<T> classe) {
    List<T> resultList = (List<T>) getEntityManager().createQuery("select e from " + classe.getSimpleName() + " e").getResultList();
    return resultList;
  }

  @Override
  public EntityManager getEntityManager() {
    if (emf == null)
      emf = Persistence.createEntityManagerFactory(UNIT_NAME);
    if (em == null)
      em = emf.createEntityManager();
    return em;
  }
}
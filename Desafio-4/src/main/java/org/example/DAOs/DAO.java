package org.example.DAOs;

import org.example.Entidades.Pessoa;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class DAO {

    private Session session =  HibernateSession.getSessionFactory().openSession();

    public Pessoa findById(int id){
        Criteria criteria = session.createCriteria(Pessoa.class);
        criteria.add(Restrictions.eq("id", id));
        return (Pessoa) criteria.uniqueResult();
    }

   @SuppressWarnings("unchecked")
   public List<Pessoa> findAll() {
       Criteria criteria = session.createCriteria(Pessoa.class);
       return criteria.list();
   }

    public void insert(Pessoa pessoa){
        session.beginTransaction();
        session.persist(pessoa);
        session.getTransaction().commit();
    }

    public void remove(Pessoa pessoa){
        session.getTransaction().begin();
        session.delete(pessoa);
        session.getTransaction().commit();
    }

    public void closeDAO(){
        session.close();
    }
}
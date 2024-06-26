package org.example.infra.repository.pessoa;

import org.example.Entidades.Pessoa;
import org.example.infra.Utilidades.HibernateSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import java.util.List;

public class PessoaRepositoryImpl implements PessoaRepository {

    private final Session session =  HibernateSession.getSessionFactory().openSession();

    public PessoaRepositoryImpl(){
    }

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

    @SuppressWarnings("unchecked")
    public List<Pessoa> getList() {
        Criteria criteria = session.createCriteria(Pessoa.class);
        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.property("id").as("id"));
        projList.add(Projections.property("nome").as("nome"));
        projList.add(Projections.property("cpf").as("cpf"));
        projList.add(Projections.property("email").as("email"));
        criteria.setProjection(projList);
        criteria.setResultTransformer(new AliasToBeanResultTransformer(Pessoa.class));
        return criteria.list();
    }

    public void insert(Pessoa pessoa){
        session.beginTransaction();
        session.persist(pessoa);
        session.getTransaction().commit();
    }

    public void merge(Pessoa pessoa){
        session.beginTransaction();
        session.merge(pessoa);
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
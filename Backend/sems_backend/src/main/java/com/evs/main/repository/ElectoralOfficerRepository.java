package com.evs.main.repository;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.evs.main.model.Application;
import com.evs.main.model.ElectoralOfficer;
import com.evs.main.model.UserProfile;

@Repository
public class ElectoralOfficerRepository {
	
	private SessionFactory sessionFactory;

    @Autowired
    public ElectoralOfficerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	// ElectoralOfficer methods
    public void saveElectoralOfficer(ElectoralOfficer officer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(officer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateElectoralOfficer(ElectoralOfficer officer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(officer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteElectoralOfficer(String officerId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            ElectoralOfficer officer = session.get(ElectoralOfficer.class, officerId);
            if (officer != null) {
                session.delete(officer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public ElectoralOfficer findElectoralOfficerById(String officerId) {
        ElectoralOfficer officer = null;
        try (Session session = sessionFactory.openSession()) {
            officer = session.get(ElectoralOfficer.class, officerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return officer;
    }

    public List<ElectoralOfficer> findAllElectoralOfficers() {
        List<ElectoralOfficer> officersList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {
            Query<ElectoralOfficer> query = session.createQuery("from ElectoralOfficer", ElectoralOfficer.class);
            officersList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return officersList;
    }
}
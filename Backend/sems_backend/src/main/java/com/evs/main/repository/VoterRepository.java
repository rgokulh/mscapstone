package com.evs.main.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.evs.main.model.UserCredentials;
import com.evs.main.model.UserProfile;

import java.util.Collections;
import java.util.List;

@Repository
public class VoterRepository {
    
    private SessionFactory sessionFactory;

    @Autowired
    public VoterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // UserCredentials methods
    public void saveCredentials(UserCredentials userCredentials) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(userCredentials);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateCredentials(UserCredentials userCredentials) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(userCredentials);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCredentials(String userId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            UserCredentials userCredentials = session.get(UserCredentials.class, userId);
            if (userCredentials != null) {
                session.delete(userCredentials);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public UserCredentials findCredentialsById(String userId) {
        UserCredentials userCredentials = null;
        try (Session session = sessionFactory.openSession()) {
            userCredentials = session.get(UserCredentials.class, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userCredentials;
    }

    public List<UserCredentials> findAllCredentials() {
        List<UserCredentials> userCredentialsList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {
            Query<UserCredentials> query = session.createQuery("from UserCredentials", UserCredentials.class);
            userCredentialsList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userCredentialsList;
    }

    // UserProfile methods
    public void saveProfile(UserProfile userProfile) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(userProfile);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateProfile(UserProfile userProfile) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(userProfile);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteProfile(String userId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            UserProfile userProfile = session.get(UserProfile.class, userId);
            if (userProfile != null) {
                session.delete(userProfile);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public UserProfile findProfileById(String userId) {
    	UserProfile userProfile = null;
    	try (Session session = sessionFactory.openSession()) {
    		userProfile = session.get(UserProfile.class, userId);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return userProfile;
    }
    
    public List<UserProfile> findAllProfiles() {
        List<UserProfile> userProfileList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {
            Query<UserProfile> query = session.createQuery("from UserProfile", UserProfile.class);
            userProfileList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfileList;
    }
}
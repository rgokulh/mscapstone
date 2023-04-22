package com.evs.main.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.evs.main.model.Application;
import com.evs.main.model.Candidate;
import com.evs.main.model.Election;
import com.evs.main.model.ElectoralOfficer;
import com.evs.main.model.Party;
import com.evs.main.model.Result;
import com.evs.main.model.UserCredentials;
import com.evs.main.model.VoterDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class AdministratorRepository {
    
    private SessionFactory sessionFactory;

    // Best practice is constructor-dependency injection
    @Autowired
    public AdministratorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    // Authentication method
    public boolean authenticate(UserCredentials userCredentials)  throws Exception
	{
    	Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) {
	    	transaction=session.beginTransaction();
	    	Query q=session.createQuery("from UserCredentials where userId=:uid and password=:pwd");
	    	q.setParameter("uid", userCredentials.getUserId());
	    	q.setParameter("pwd",userCredentials.getPassword());
	    	
	    	ArrayList<UserCredentials> al=(ArrayList<UserCredentials>) q.getResultList();
	    	int count=0;
	    	for(UserCredentials uc:al)
	    	{
				if((uc.getUserId().equals(userCredentials.getUserId()))&&(uc.getPassword().equals(userCredentials.getPassword())))
				{
					count++;
					if(count>0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
	    	}
	    	return false;
	 
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw new Exception("Error authenticating user with ID: " + userCredentials.getUserId() + " in the repository layer", e);
	    }
	}
    
    // Application methods
    public void saveApplication(Application application) {
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) {
		    transaction = session.beginTransaction();
		    session.save(application);
		    transaction.commit();
	    } catch (Exception e) {
		    if (transaction != null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace();
	    }
    }

    public void updateApplication(Application application) {
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) {
		    transaction = session.beginTransaction();
		    session.update(application);
		    transaction.commit();
	    } catch (Exception e) {
		    if (transaction != null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace();
	    }
    }

    public void deleteApplication(String userId) {
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) {
		    transaction = session.beginTransaction();
		    Application application = session.get(Application.class, userId);
		    if (application != null) {
		    	session.delete(application);
		    }
		    transaction.commit();
	    } catch (Exception e) {
	    	if (transaction != null) {
	    		transaction.rollback();
	    	}
	    	e.printStackTrace();
	    }
    }

    public Application findApplicationById(String userId) {
	    Application application = null;
	    try (Session session = sessionFactory.openSession()) {
	    	application = session.get(Application.class, userId);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return application;
    }

    public List<Application> findAllApplications() {
    	List<Application> applicationList = Collections.emptyList();
    	try (Session session = sessionFactory.openSession()) {
    		Query<Application> query = session.createQuery("from Application", Application.class);
    		applicationList = query.getResultList();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return applicationList;
    }
    
    // Candidate methods
    public void saveCandidate(Candidate candidate) {
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) {
		    transaction = session.beginTransaction();
		    session.save(candidate);
		    transaction.commit();
	    } catch (Exception e) {
		    if (transaction != null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace();
	    }
    }

    public void updateCandidate(Candidate candidate) {
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) {
		    transaction = session.beginTransaction();
		    session.update(candidate);
		    transaction.commit();
	    } catch (Exception e) {
		    if (transaction != null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace();
	    }
    }

    public void deleteCandidate(String candidateId) {
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) {
		    transaction = session.beginTransaction();
		    Candidate candidate = session.get(Candidate.class, candidateId);
		    if (candidate != null) {
		    	session.delete(candidate);
		    }
		    transaction.commit();
	    } catch (Exception e) {
	    	if (transaction != null) {
	    		transaction.rollback();
	    	}
	    	e.printStackTrace();
	    }
    }

    public Candidate findCandidateById(String candidateId) {
	    Candidate candidate = null;
	    try (Session session = sessionFactory.openSession()) {
	    	candidate = session.get(Candidate.class, candidateId);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return candidate;
    }

    public List<Candidate> findAllCandidates() {
    	List<Candidate> candidateList = Collections.emptyList();
    	try (Session session = sessionFactory.openSession()) {
    		Query<Candidate> query = session.createQuery("from Candidate", Candidate.class);
    		candidateList = query.getResultList();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return candidateList;
    }
    
    public ArrayList<Candidate> findCandidatesByPartyId(String partyId) {
        ArrayList<Candidate> candidates = null;
        try (Session session = sessionFactory.openSession()) {
            Party party = findPartyById(partyId);
            if (party != null && party.getPartyid().equals(partyId)) {
                Query<Candidate> q1 = session.createQuery("from Candidate c where c.party.partyid = :partyId", Candidate.class);
                q1.setParameter("partyId", partyId);
                candidates = (ArrayList<Candidate>) q1.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public ArrayList<Candidate> findCandidatesByElectionName(String name, String constituency, String electionId) {
        ArrayList<Candidate> candidates = null;
        try (Session session = sessionFactory.openSession()) {
            Election election = findElectionById(electionId);
            if (election != null && election.getName().equals(name) && election.getConstituency().equals(constituency)) {
                Query<Candidate> q1 = session.createQuery("from Candidate c where c.election.electionid = :electionId", Candidate.class);
                q1.setParameter("electionId", electionId);
                candidates = (ArrayList<Candidate>) q1.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }
    
    // Election methods
    public void saveElection(Election election) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(election);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateElection(Election election) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(election);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteElection(String electionId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Election election = session.get(Election.class, electionId);
            if (electionId != null) {
                session.delete(election);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Election findElectionById(String electionId) {
        Election election = null;
        try (Session session = sessionFactory.openSession()) {
            election = session.get(Election.class, electionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return election;
    }

    public List<Election> findAllElections() {
        List<Election> electionList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {
            Query<Election> query = session.createQuery("from Election", Election.class);
            electionList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return electionList;
    }
    
    // Party methods
    public void saveParty(Party party) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(party);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateParty(Party party) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(party);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteParty(String partyId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Party party = session.get(Party.class, partyId);
            if (partyId != null) {
                session.delete(party);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Party findPartyById(String partyId) {
        Party party = null;
        try (Session session = sessionFactory.openSession()) {
        	party = session.get(Party.class, partyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return party;
    }

    public List<Party> findAllPartys() {
        List<Party> partyList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {
            Query<Party> query = session.createQuery("from Party", Party.class);
            partyList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partyList;
    }
    
    // Result methods
    public void saveResult(Result result) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateResult(Result result) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteResult(String serialNo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Result result = session.get(Result.class, serialNo);
            if (serialNo != null) {
                session.delete(result);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Result findResultById(String serialNo) {
        Result result = null;
        try (Session session = sessionFactory.openSession()) {
        	result = session.get(Result.class, serialNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Result> findAllResults() {
        List<Result> resultList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {
            Query<Result> query = session.createQuery("from Result", Result.class);
            resultList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    
    // VoterDetails methods
    public void saveVoterDetails(VoterDetails voterDetails) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(voterDetails);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateVoterDetails(VoterDetails voterDetails) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(voterDetails);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteVoterDetails(String serialNo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            VoterDetails voterDetails = session.get(VoterDetails.class, serialNo);
            if (serialNo != null) {
                session.delete(voterDetails);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public VoterDetails findVoterDetailsById(String serialNo) {
    	VoterDetails voterDetails = null;
        try (Session session = sessionFactory.openSession()) {
        	voterDetails = session.get(VoterDetails.class, serialNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voterDetails;
    }

    public List<VoterDetails> findAllVoterDetails() {
        List<VoterDetails> voterList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {
            Query<VoterDetails> query = session.createQuery("from VoterDetails", VoterDetails.class);
            voterList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voterList;
    }
}
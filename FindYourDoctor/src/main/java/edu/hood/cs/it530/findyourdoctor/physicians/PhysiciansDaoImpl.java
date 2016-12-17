package edu.hood.cs.it530.findyourdoctor.physicians;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.EducationalQualification;
import edu.hood.cs.it530.findyourdoctor.common.beans.Insurance;
import edu.hood.cs.it530.findyourdoctor.common.beans.InsuranceReview;
import edu.hood.cs.it530.findyourdoctor.common.beans.PatientReview;
import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;
import edu.hood.cs.it530.findyourdoctor.common.beans.Speciality;
import edu.hood.cs.it530.findyourdoctor.educationalQualifications.EducationalQualificationsDao;
import edu.hood.cs.it530.findyourdoctor.insurances.InsurancesDao;
import edu.hood.cs.it530.findyourdoctor.patients.PatientsDao;
import edu.hood.cs.it530.findyourdoctor.specialities.SpecialitiesDao;

/**
 * @author kisna
 *
 */
@Component
public class PhysiciansDaoImpl extends AbstractDao implements PhysicianDao {

    private SpecialitiesDao specialitiesDao;

    private EducationalQualificationsDao educationalQualificationsDao;

    private InsurancesDao insurancesDao;
    
    private PatientsDao patientsDao;


    public PhysiciansDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Autowired
    public void setSpecialitiesDao(SpecialitiesDao specialitiesDao) {
        this.specialitiesDao = specialitiesDao;
    }


    /**
     * @param insurancesDao
     *            the reviewDao to set
     */
    @Autowired
    public void setReviewDao(InsurancesDao insurancesDao) {
        this.insurancesDao = insurancesDao;
    }
    
    @Autowired
    public void setEducationalQualificationsDao(EducationalQualificationsDao educationalQualificationsDao) {
        this.educationalQualificationsDao = educationalQualificationsDao;
    }

    /**
     * @param patientsDao the patientsDao to set
     */
    @Autowired
    public void setPatientsDao(PatientsDao patientsDao) {
        this.patientsDao = patientsDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.hood.cs.it530.findyourdoctor.physicians.PhysicianDao#
     * retrievePhysicians(int, int, java.lang.String, java.lang.String)
     */
    @Override
    public List<Physician> retrievePhysicians(int zipCode, int specialityId, String firstName, String lastName,
            String city) throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
        if (zipCode != 0) {
            namedParameters.put("zip_code", zipCode);
        }
        if (specialityId != 0) {
            namedParameters.put("speciality_id", specialityId);
        }

        if (firstName != null && firstName.length() != 0) {
            namedParameters.put("first_name", "%" + firstName + "%");
        }

        if (lastName != null && lastName.length() != 0) {
            namedParameters.put("last_name", "%" + lastName + "%");
        }

        if (city != null && city.length() != 0) {
            namedParameters.put("city", "%" + city + "%");
        }

        String physicianSearchQuery = "";
        physicianSearchQuery += "SELECT \n";
        physicianSearchQuery += "    p.physician_id,\n";
        physicianSearchQuery += "    p.first_name,\n";
        physicianSearchQuery += "    p.last_name,\n";
        physicianSearchQuery += "    p.middle_initial,\n";
        physicianSearchQuery += "    p.location_id,\n";
        physicianSearchQuery += "    l.city,\n";
        physicianSearchQuery += "    l.street,\n";
        physicianSearchQuery += "    l.suite_number,\n";
        physicianSearchQuery += "    l.zip_code,\n";
        physicianSearchQuery += "    l.state,\n";
        physicianSearchQuery += "    l.phone_number,\n";
        physicianSearchQuery += "    ps.speciality_id,\n";
        physicianSearchQuery += "    s.speciality_name \n";
        physicianSearchQuery += "FROM\n";
        physicianSearchQuery += "    physicians p\n";
        physicianSearchQuery += "        JOIN\n";
        physicianSearchQuery += "    locations l ON p.location_id = l.location_id\n";
        physicianSearchQuery += "        LEFT JOIN\n    rln_physician_speciality ps ON ps.physician_id = p.physician_id \n";

        if (specialityId != 0) {
            physicianSearchQuery += "    AND ps.speciality_id = :speciality_id\n";
        }

        physicianSearchQuery += "        JOIN\n";

        physicianSearchQuery += "    specialities s ON ps.speciality_id = s.speciality_id\n";
        physicianSearchQuery += "WHERE\n";
        physicianSearchQuery += "    1 = 1 \n";
        if (zipCode != 0) {
            physicianSearchQuery += "    AND l.zip_code = :zip_code\n";
        }
        if (firstName != null && firstName.length() != 0) {
            physicianSearchQuery += "    AND p.first_name LIKE :first_name \n";
        }
        if (lastName != null && lastName.length() != 0) {
            physicianSearchQuery += "    AND p.last_name LIKE :last_name \n";
        }
        if (city != null && city.length() != 0) {
            physicianSearchQuery += "    AND l.city  LIKE  :city \n";
        }        
        physicianSearchQuery += "ORDER BY trim(last_name) ASC , trim(first_name) ASC \n";

        System.out.println(physicianSearchQuery);
        System.out.println(namedParameters);

        List<Physician> physicians = getNamedParameterJdbcTemplate().query(physicianSearchQuery, namedParameters,
                new PhysiciansMapper());

        return physicians;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.hood.cs.it530.findyourdoctor.physicians.PhysicianDao#
     * retrievePhysician(edu.hood.cs.it530.findyourdoctor.common.beans.
     * Physician)
     */
    @Override
    public Physician retrievePhysician(Physician physician) {

        String retrievePhysicianStatement = "";
        retrievePhysicianStatement += "SELECT \n";
        retrievePhysicianStatement += "    physician_id,\n";
        retrievePhysicianStatement += "    first_name,\n";
        retrievePhysicianStatement += "    middle_initial,\n";
        retrievePhysicianStatement += "    last_name,\n";
        retrievePhysicianStatement += "    location_id\n";
        retrievePhysicianStatement += "FROM\n";
        retrievePhysicianStatement += "    physicians\n";
        retrievePhysicianStatement += "WHERE\n";
        retrievePhysicianStatement += "    first_name = :first_name\n";
        retrievePhysicianStatement += "        AND last_name = :last_name\n";
        retrievePhysicianStatement += "        AND middle_initial = :middle_initial\n";
        retrievePhysicianStatement += "        AND location_id = :location_id";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("first_name", physician.getFirstName());
        namedParameters.put("middle_initial", physician.getMiddleInitial());
        namedParameters.put("last_name", physician.getLastName());
        namedParameters.put("location_id", physician.getLocation().getLocationId());

        Physician physicianResult = getNamedParameterJdbcTemplate().queryForObject(retrievePhysicianStatement,
                namedParameters, new PhysicianMapper());

        physician.setPhysicianId(physicianResult.getPhysicianId());

        return physicianResult;

    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.hood.cs.it530.findyourdoctor.physicians.PhysicianDao#
     * retrievePhysician(edu.hood.cs.it530.findyourdoctor.common.beans.
     * Physician)
     */
    @Override
    public Physician retrievePhysicianDetails(int physicianId) {

        String retrievePhysicianStatement = "";
        retrievePhysicianStatement += "SELECT \n " + "   p.physician_id,\n" + "    p.first_name,\n"
                + "    p.last_name,\n" + "    p.middle_initial,\n" + "    p.location_id,\n" + "    l.city,\n"
                + "    l.street,\n" + "    l.suite_number,\n" + "    l.zip_code,\n" + "    l.state,\n"
                + "    l.phone_number\n" + "FROM\n" + "    physicians p\n" + "        JOIN\n"
                + "    locations l ON p.location_id = l.location_id\n" + "    WHERE  p.physician_id = :physician_id";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("physician_id", physicianId);

        Physician physicianResult = getNamedParameterJdbcTemplate().queryForObject(retrievePhysicianStatement,
                namedParameters, new PhysicianLocationMapper());

        List<Speciality> specialities = specialitiesDao.getSpecialitiesForAPhysician(physicianId);

        List<EducationalQualification> educationalQualifications = educationalQualificationsDao
                .getEducationalQualificationsForAPhysician(physicianId);
        
        List<InsuranceReview> insuranceReviews = insurancesDao.getInsuranceReviewForAPhysician(physicianId);
        
        List<Insurance> acceptedInsurances = insurancesDao.retrieveAcceptedInsurancesByAPhysician(physicianId);
        
        List<PatientReview> patientReviews = patientsDao.retrievePatientReviewForAPhysician(physicianId);

        physicianResult.setSpecialities(specialities);

        physicianResult.setEducationalQualifications(educationalQualifications);
        
        physicianResult.setInsuranceReviews(insuranceReviews);
        
        physicianResult.setAcceptedInsurances(acceptedInsurances);
        
        physicianResult.setPatientReviews(patientReviews);

        return physicianResult;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.hood.cs.it530.findyourdoctor.physicians.PhysicianDao#insertPhysician(
     * edu.hood.cs.it530.findyourdoctor.common.beans.Physician)
     */
    @Override
    @Transactional
    public void insertPhysician(Physician physician) {

        String insertIntoPhysicianStatement = "";

        insertIntoPhysicianStatement += "INSERT IGNORE INTO physicians \n";
        insertIntoPhysicianStatement += "( \n";
        insertIntoPhysicianStatement += "       first_name, \n";
        insertIntoPhysicianStatement += "       middle_initial, \n";
        insertIntoPhysicianStatement += "       last_name, \n";
        insertIntoPhysicianStatement += "       location_id) \n";
        insertIntoPhysicianStatement += " VALUES \n";
        insertIntoPhysicianStatement += "( \n";
        insertIntoPhysicianStatement += "       :first_name, \n";
        insertIntoPhysicianStatement += "       :middle_initial, \n";
        insertIntoPhysicianStatement += "       :last_name, \n";
        insertIntoPhysicianStatement += "       :location_id \n";
        insertIntoPhysicianStatement += ") \n";

        Map<String, Object> namedParameters = new HashMap<>();

        namedParameters.put("first_name", physician.getFirstName());
        namedParameters.put("middle_initial", physician.getMiddleInitial());
        namedParameters.put("last_name", physician.getLastName());
        namedParameters.put("location_id", physician.getLocation().getLocationId());

        getNamedParameterJdbcTemplate().execute(insertIntoPhysicianStatement, namedParameters,
                ps -> ps.executeUpdate());

        retrievePhysician(physician);

        String deleteSpecialitiesForAPhysician = "";
        deleteSpecialitiesForAPhysician += "delete from rln_physician_speciality \n";
        deleteSpecialitiesForAPhysician += "    where physician_id = :physician_id";

        Map<String, Object> deletePhysicianSpecialityParams = new HashMap<>();
        deletePhysicianSpecialityParams.put("physician_id", physician.getPhysicianId());

        getNamedParameterJdbcTemplate().execute(deleteSpecialitiesForAPhysician, deletePhysicianSpecialityParams,
                ps -> ps.executeUpdate());

        List<Speciality> specialities = physician.getSpecialities();
        for (Speciality speciality : specialities) {
            String insertPhysicianSpecialityMapping = "";
            insertPhysicianSpecialityMapping += "INSERT IGNORE INTO rln_physician_speciality \n";
            insertPhysicianSpecialityMapping += "( \n";
            insertPhysicianSpecialityMapping += "   speciality_id, \n";
            insertPhysicianSpecialityMapping += "   physician_id) \n";
            insertPhysicianSpecialityMapping += "VALUES \n";
            insertPhysicianSpecialityMapping += "   ( \n";
            insertPhysicianSpecialityMapping += "      :speciality_id, \n";
            insertPhysicianSpecialityMapping += "      :physician_id  \n";
            insertPhysicianSpecialityMapping += ") \n";

            Map<String, Object> specialityParams = new HashMap<>();

            specialityParams.put("speciality_id", speciality.getSpecialityId());
            specialityParams.put("physician_id", physician.getPhysicianId());

            getNamedParameterJdbcTemplate().execute(insertPhysicianSpecialityMapping, specialityParams,
                    ps -> ps.executeUpdate());

        }

    }

    @Override
    public void deletePhysician(int physicianId) {
        
        String retrievePhysicianStatement = "";
        retrievePhysicianStatement += "DELETE FROM physicians WHERE physician_id = :physician_id";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("physician_id", physicianId);
        
        getNamedParameterJdbcTemplate().execute(retrievePhysicianStatement, namedParameters,
                ps -> ps.executeUpdate());
        
        
    }

}

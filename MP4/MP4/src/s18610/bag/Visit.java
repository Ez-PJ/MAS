/**
 * @Author: Patryk Kamiński
 */

package s18610.bag;

public class Visit
{
    private String dateOfVisit;

    private Patient patient;
    private Clinic clinic;

    public Visit(String dateOfVisit, Patient patient, Clinic clinic)
    {
        this.dateOfVisit = dateOfVisit;
        this.patient = patient;
        this.clinic = clinic;

        patient.addVisit(this);
        clinic.addVisit(this);
    }

    public String getDateOfVisit()
    {
        return dateOfVisit;
    }
    public Patient getPatient()
    {
        return patient;
    }
    public Clinic getClinic()
    {
        return clinic;
    }


    @Override
    public String toString()
    {
        return "Date: " + getDateOfVisit() +
               "\n" + "patient: " + getPatient() +
                "\n" + "clinic: " + getClinic();
    }

}


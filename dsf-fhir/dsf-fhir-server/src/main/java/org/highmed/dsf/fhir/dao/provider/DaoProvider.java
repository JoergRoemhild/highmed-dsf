package org.highmed.dsf.fhir.dao.provider;

import java.util.Optional;

import org.highmed.dsf.fhir.dao.BundleDao;
import org.highmed.dsf.fhir.dao.CodeSystemDao;
import org.highmed.dsf.fhir.dao.EndpointDao;
import org.highmed.dsf.fhir.dao.HealthcareServiceDao;
import org.highmed.dsf.fhir.dao.LocationDao;
import org.highmed.dsf.fhir.dao.OrganizationDao;
import org.highmed.dsf.fhir.dao.PatientDao;
import org.highmed.dsf.fhir.dao.PractitionerDao;
import org.highmed.dsf.fhir.dao.PractitionerRoleDao;
import org.highmed.dsf.fhir.dao.ProvenanceDao;
import org.highmed.dsf.fhir.dao.ResearchStudyDao;
import org.highmed.dsf.fhir.dao.ResourceDao;
import org.highmed.dsf.fhir.dao.StructureDefinitionDao;
import org.highmed.dsf.fhir.dao.StructureDefinitionSnapshotDao;
import org.highmed.dsf.fhir.dao.SubscriptionDao;
import org.highmed.dsf.fhir.dao.TaskDao;
import org.highmed.dsf.fhir.dao.ValueSetDao;
import org.hl7.fhir.r4.model.DomainResource;

public interface DaoProvider
{
	BundleDao getBundleDao();

	CodeSystemDao getCodeSystemDao();

	EndpointDao getEndpointDao();

	HealthcareServiceDao getHealthcareServiceDao();

	LocationDao getLocationDao();

	OrganizationDao getOrganizationDao();

	PatientDao getPatientDao();

	PractitionerDao getPractitionerDao();

	PractitionerRoleDao getPractitionerRoleDao();

	ProvenanceDao getProvenanceDao();

	ResearchStudyDao getResearchStudyDao();

	StructureDefinitionDao getStructureDefinitionDao();

	StructureDefinitionSnapshotDao getStructureDefinitionSnapshotDao();

	SubscriptionDao getSubscriptionDao();

	TaskDao getTaskDao();

	ValueSetDao getValueSetDao();

	<R extends DomainResource> Optional<? extends ResourceDao<R>> getDao(Class<R> resourceClass);

	Optional<ResourceDao<?>> getDao(String resourceTypeName);
}
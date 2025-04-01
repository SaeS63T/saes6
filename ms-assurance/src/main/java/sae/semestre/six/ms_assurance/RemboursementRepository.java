package sae.semestre.six.ms_assurance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "remboursements", path = "remboursement")
public interface RemboursementRepository extends JpaRepository<Remboursement , Integer> {

    @Query("select r from Remboursement r where r.nomProduit = :nomProduit and r.nomAssurance = :nomAssurance")
    public Page<Remboursement> remboursementByNomProduitAndNomAssurance(@Param("nomProduit") String nomProduit, @Param("nomAssurance") String nomAssurance, Pageable pageable);
}
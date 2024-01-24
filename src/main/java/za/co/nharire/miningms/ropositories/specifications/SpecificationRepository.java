package za.co.nharire.miningms.ropositories.specifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.specification.Specification;
@Repository

public interface SpecificationRepository extends JpaRepository<Specification,Long> {
}

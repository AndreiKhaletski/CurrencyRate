package finance.dao.api;


import finance.dao.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IRateResource extends JpaRepository<RateEntity, UUID> {

    boolean existsByDateAndCurAbbreviation(LocalDate date, String curAbbreviation);

    RateEntity findByDateAndCurAbbreviation(LocalDate dataTime, String abbreviation);

    boolean existsByDate(LocalDate date);

    List<RateEntity> findAllByDate(LocalDate date);
}

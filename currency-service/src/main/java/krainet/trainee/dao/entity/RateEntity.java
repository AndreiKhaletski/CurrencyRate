package krainet.trainee.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(schema = "app",name = "courses")
public class RateEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "cur_id")
    @JsonProperty("Cur_ID")
    private Integer curID;

    @Column(name = "date")
    @JsonProperty("Date")
    private LocalDate date;

    @Column(name = "cur_abbreviation")
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;

    @Column(name = "cur_scale")
    @JsonProperty("Cur_Scale")
    private Integer curScale;

    @Column(name = "cur_name")
    @JsonProperty("Cur_Name")
    private String curName;

    @Column(name = "cur_official_rate")
    @JsonProperty("Cur_OfficialRate")
    private Double curOfficialRate;

    public RateEntity() {
    }

    public RateEntity(UUID uuid,
                      Integer curID,
                      LocalDate date,
                      String curAbbreviation,
                      Integer curScale,
                      String curName,
                      Double curOfficialRate) {
        this.uuid = uuid;
        this.curID = curID;
        this.date = date;
        this.curAbbreviation = curAbbreviation;
        this.curScale = curScale;
        this.curName = curName;
        this.curOfficialRate = curOfficialRate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getCurID() {
        return curID;
    }

    public void setCurID(Integer curID) {
        this.curID = curID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public Integer getCurScale() {
        return curScale;
    }

    public void setCurScale(Integer curScale) {
        this.curScale = curScale;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public Double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurOfficialRate(Double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }
}

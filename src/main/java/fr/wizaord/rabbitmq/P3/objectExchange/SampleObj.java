package fr.wizaord.rabbitmq.P3.objectExchange;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id2", scope = SampleObj.class)
public class SampleObj {

    private long id;
    private String message;
    private List<SubSampleObj> subSampleObjList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SubSampleObj> getSubSampleObjList() {
        return subSampleObjList;
    }

    public void setSubSampleObjList(List<SubSampleObj> subSampleObjList) {
        this.subSampleObjList = subSampleObjList;
    }

    public void addSub(String plop) {
        final SubSampleObj sm = new SubSampleObj(this);
        sm.setId(UUID.randomUUID().getMostSignificantBits());
        sm.setMessage(plop);
        this.subSampleObjList.add(sm);
    }
}

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id2", scope = SubSampleObj.class)
class SubSampleObj {
    private long id;
    private String message;
    private SampleObj pere;

    public SubSampleObj() {
        super();
    }

    public SubSampleObj(SampleObj pere) {
        this.pere = pere;
    }

    public SampleObj getPere() {
        return pere;
    }

    public void setPere(SampleObj pere) {
        this.pere = pere;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

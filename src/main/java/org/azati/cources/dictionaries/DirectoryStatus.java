package org.azati.cources.dictionaries;

import org.azati.cources.entity.Room;
import org.azati.cources.enums.StatusRoom;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "linked_room_status")
public class DirectoryStatus {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "link_id")
    private Integer linkId;

    @Column(name = "varchar_status")
    private String varcharRoomStatus;

    @Column(name = "enum_status")
    @Enumerated(EnumType.STRING)
    private StatusRoom statusRoom;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "directoryStatus")
    private List<Room> rooms;

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getVarcharRoomStatus() {
        return varcharRoomStatus;
    }

    public void setVarcharRoomStatus(String varcharRoomStatus) {
        this.varcharRoomStatus = varcharRoomStatus;
    }

    public StatusRoom getStatusRoom() {
        return statusRoom;
    }

    public void setStatusRoom(StatusRoom statusRoom) {
        this.statusRoom = statusRoom;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "DirectoryStatus{" +
                "linkId=" + linkId +
                ", varcharRoomStatus='" + varcharRoomStatus + '\'' +
                ", statusRoom=" + statusRoom +
                ", rooms=" + rooms +
                '}';
    }
}

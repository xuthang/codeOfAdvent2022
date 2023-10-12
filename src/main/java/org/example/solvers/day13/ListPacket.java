package org.example.solvers.day13;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ListPacket extends Packet {
    List<Packet> packets;

    public ListPacket(List<Packet> packets) {
        this.packets = packets;
    }

    @Override
    public String toString() {
        return packets.toString();
    }

    @Override
    public int compareTo(@NotNull Packet o) {
        if(o.getClass() == OnePacket.class)
            return compareTo(((OnePacket) o).convertToList());

        ListPacket other = (ListPacket) o;

        int commonBound = Integer.min(packets.size(), other.packets.size());
        for (int i = 0; i < commonBound; i++) {
            int res = packets.get(i).compareTo(other.packets.get(i));
            if (res != 0)
                return res;
        }

        if (packets.size() == other.packets.size())
            return Constants.SAME;
        return packets.size() < other.packets.size() ? Constants.RIGHT_ORDER : Constants.WRONG_ORDER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListPacket that = (ListPacket) o;
        return Objects.equals(packets, that.packets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packets);
    }
}

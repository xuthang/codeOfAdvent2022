package org.example.solvers.day13;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnePacket extends Packet {
    int val;

    public OnePacket(int val) {
        this.val = val;
    }

    ListPacket convertToList() {
        List<Packet> l = new ArrayList<>();
        l.add(this);
        return new ListPacket(l);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public int compareTo(@NotNull Packet o) {
        if (o.getClass() == OnePacket.class) {
            if (val == ((OnePacket) o).val)
                return Constants.SAME;
            return val < ((OnePacket) o).val ? Constants.RIGHT_ORDER : Constants.WRONG_ORDER;
        }

        return this.convertToList().compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnePacket onePacket = (OnePacket) o;
        return val == onePacket.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}

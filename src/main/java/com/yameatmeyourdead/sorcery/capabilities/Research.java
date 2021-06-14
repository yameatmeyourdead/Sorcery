package com.yameatmeyourdead.sorcery.capabilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;

public class Research {
    private NonNullList<String> research;
    private int insanity; 

    public Research() {
        this.research = NonNullList.create();
        this.insanity = 0;
    }

    public String getResearch() {return String.join(";", this.research);}
    public NonNullList<String> getProtectedResearch() {
        NonNullList<String> cpy = NonNullList.create();
        this.research.forEach(element -> cpy.add(element));
        return cpy;
    }
    public void setResearch(NonNullList<String> research) {this.research = research;}
    public void addResearch(String researchName) {this.research.add(researchName);}

    public int getInsanity() {return this.insanity;}
    public void setInsanity(int insanity) {this.insanity = insanity;}
    public void addInsanity(int add) {this.insanity += add;}
    public void decreaseInsanity(int decr) {this.insanity -= decr;}

    public void setData(NonNullList<String> _research, int _insanity) {
        this.research = NonNullList.create();
        _research.forEach(e -> this.research.add(e));
        this.insanity = _insanity;
    }

    public static class ResearchStorage implements Capability.IStorage<Research> {

        @Override
        public INBT writeNBT(Capability<Research> capability, Research instance, Direction side) {
            CompoundNBT tags = new CompoundNBT();
            tags.putInt("insanity", instance.insanity);
            tags.putString("research", String.join(";", instance.research));
            return tags;
        }

        @Override
        public void readNBT(Capability<Research> capability, Research instance, Direction side, INBT tag) {
            CompoundNBT tags = (CompoundNBT) tag;
            if(tags.contains("insanity")) {
                instance.insanity = tags.getInt("insanity");
            }
            if(tags.contains("research")) {
                // wow this is disgusting..... String -> String[] -> List<String> -> NonNullList<String> :)
                List<String> toAdd = Arrays.asList(tags.getString("research").split(";"));
                NonNullList<String> toReturn = NonNullList.create();
                toAdd.forEach(element -> toReturn.add(element));
                instance.research = toReturn;
            }
        }
    }

    public static Research createDefaultInstance(){
        return new Research();
    }
}

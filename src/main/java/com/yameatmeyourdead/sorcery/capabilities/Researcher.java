package com.yameatmeyourdead.sorcery.capabilities;

import java.util.Arrays;
import java.util.List;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;

public class Researcher {
    private NonNullList<String> researchCompleted;
    private int insanity; 

    public Researcher() {
        this.researchCompleted = NonNullList.create();
        this.insanity = 0;
    }

    public String getResearch() {return String.join(";", this.researchCompleted);}
    public NonNullList<String> getProtectedResearch() {
        NonNullList<String> cpy = NonNullList.create();
        this.researchCompleted.forEach(element -> cpy.add(element));
        return cpy;
    }
    public void setResearchCompleted(NonNullList<String> researchCompleted) {this.researchCompleted = researchCompleted;}
    public void addResearch(String researchName) {this.researchCompleted.add(researchName);
    }

    public int getInsanity() {return this.insanity;}
    public void setInsanity(int insanity) {this.insanity = insanity;}
    public void addInsanity(int add) {this.insanity += add;}
    public void decreaseInsanity(int decr) {this.insanity -= decr;}

    public void setData(NonNullList<String> _research, int _insanity) {
        this.researchCompleted = NonNullList.create();
        _research.forEach(e -> this.researchCompleted.add(e));
        this.insanity = _insanity;
    }

    public static class ResearchStorage implements Capability.IStorage<Researcher> {

        @Override
        public INBT writeNBT(Capability<Researcher> capability, Researcher instance, Direction side) {
            CompoundNBT tags = new CompoundNBT();
            tags.putInt("insanity", instance.insanity);
            tags.putString("research", String.join(";", instance.researchCompleted));
            return tags;
        }

        @Override
        public void readNBT(Capability<Researcher> capability, Researcher instance, Direction side, INBT tag) {
            CompoundNBT tags = (CompoundNBT) tag;
            if(tags.contains("insanity")) {
                instance.insanity = tags.getInt("insanity");
            }
            if(tags.contains("research")) {
                // wow this is disgusting..... String -> String[] -> List<String> -> NonNullList<String> :)
                List<String> toAdd = Arrays.asList(tags.getString("research").split(";"));
                NonNullList<String> toReturn = NonNullList.create();
                toAdd.forEach(element -> toReturn.add(element));
                instance.researchCompleted = toReturn;
            }
        }
    }

    public static Researcher createDefaultInstance(){
        return new Researcher();
    }
}

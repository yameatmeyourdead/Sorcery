package com.yameatmeyourdead.sorcery.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class ResearchBase extends ForgeRegistryEntry<ResearchBase> {
    // instance variables that all researches should contain data on
    protected String name = "ResearchBaseHowDidYouGetThis";
    protected int insanity = 0;
    protected List<ResearchBase> prerequisites = new ArrayList<ResearchBase>();
    
    // getters/setters for instance variables
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final int getInsanity() {return insanity;}
    public final void setInsanity(int insanity) {this.insanity = insanity;}
    public final List<ResearchBase> getPrerequisites() {return this.prerequisites;}
    public final void setPrerequisites(List<ResearchBase> prerequisites) {this.prerequisites = prerequisites;}

    // common methods
    public String toString() {
        String prereqs = "";
        if(prerequisites == null || prerequisites.size() == 0) prereqs = "NONE";
        if(prereqs != null) {
            for(ResearchBase element : prerequisites) {
                prereqs += element.toString();
            }
        }
        
        return ("name: " + this.name + ",insanity: " + String.valueOf(insanity) + "prerequisites: {" + prereqs + "}");
    }
}

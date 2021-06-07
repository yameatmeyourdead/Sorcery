package com.yameatmeyourdead.sorcery.pseudoitem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;

public class Sigil extends net.minecraftforge.registries.ForgeRegistryEntry<Sigil> {
    private String name;
    private int color;
    @Nullable
    private Sigil[] components;
    private ResourceLocation image;

    private static ArrayList<Sigil> primal = new ArrayList<Sigil>();
    private static LinkedHashMap<String, Sigil> sigils = new LinkedHashMap<String, Sigil>();

    /**
     * Base Constructor
     * @param name Name that will be used to reference this sigil
     * @param color Color to display Name with
     * @param components Components of this Sigil
    */
    public Sigil(String name, int color, @Nullable Sigil[] components, ResourceLocation image) {
        if(sigils.containsKey(name)) throw new IllegalArgumentException(name + " already registered");
        this.name = name;
        this.color = color;
        this.components = components;
        this.image = image;
    }

    /**
     * Used for base sigils
     */
    public Sigil(String name, int color, Sigil[] components) {
        this(name, color, components, new ResourceLocation("sorcery","textures/sigils/"+name.toLowerCase()+".png"));
    }

    /** 
     * Used for Primal Sigils
    */
    public Sigil(String name, int color, String chatcolor) {
        this(name, color, (Sigil[])null);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // might not be null safe consider adding @Nullable??
    public void setComponents(Sigil[] components) {
        this.components = components;
    }

    public Sigil[] getComponents() {
        return components;
    }

    public void setImage(ResourceLocation image) {
        this.image = image;
    }

    public ResourceLocation getImage() {
        return image;
    }

    // 72 Demon sigils this pains me to look at
    public static final Sigil KING_BAEL = new Sigil("king_bael", 0xffff73, "e");
    public static final Sigil DUKE_AGARES = new Sigil("duke_agares", 0xffff73, "e");
    public static final Sigil PRINCE_VASSAGO = new Sigil("prince_vassago", 0xffff73, "e");
    public static final Sigil MARQUIS_SAMIGINA = new Sigil("marquis_samigina", 0xffff73, "e");
    public static final Sigil PRESIDENT_MARBAS = new Sigil("president_marbas", 0xffff73, "e");
    public static final Sigil DUKE_VALEFOR = new Sigil("duke_valefor", 0xffff73, "e");
    public static final Sigil MARQUIS_AMON = new Sigil("marquis_amon", 0xffff73, "e");
    public static final Sigil DUKE_BARBATOS = new Sigil("duke_barbatos", 0xffff73, "e");
    public static final Sigil KING_PAIMON = new Sigil("king_paimon", 0xffff73, "e");
    public static final Sigil PRESIDENT_BUER = new Sigil("president_buer", 0xffff73, "e");
    public static final Sigil DUKE_GUSION = new Sigil("duke_gusion", 0xffff73, "e");
    public static final Sigil PRINCE_SITRI = new Sigil("prince_sitri", 0xffff73, "e");
    public static final Sigil KING_BELETH = new Sigil("king_beleth", 0xffff73, "e");
    public static final Sigil MARQUIS_LERAJE = new Sigil("marquis_leraje", 0xffff73, "e");
    public static final Sigil DUKE_ELIGOS = new Sigil("duke_eligos", 0xffff73, "e");
    public static final Sigil DUKE_ZEPAR = new Sigil("duke_zepar", 0xffff73, "e");
    public static final Sigil COUNT_BOTIS = new Sigil("count_botis", 0xffff73, "e");
    public static final Sigil DUKE_BATHIN = new Sigil("duke_bathin", 0xffff73, "e");
    public static final Sigil DUKE_SALLOS = new Sigil("duke_sallos", 0xffff73, "e");
    public static final Sigil KING_PURSON = new Sigil("king_purson", 0xffff73, "e");
    public static final Sigil COUNT_MORAX = new Sigil("count_morax", 0xffff73, "e");
    public static final Sigil PRINCE_IPOS = new Sigil("count/prince_ipos", 0xffff73, "e");
    public static final Sigil DUKE_AIM = new Sigil("duke_aim", 0xffff73, "e");
    public static final Sigil MARQUIS_NABERIUS = new Sigil("marquis_naberius", 0xffff73, "e");
    public static final Sigil COUNT_GLASYA_LABOLAS = new Sigil("count_glasya_labolas", 0xffff73, "e");
    public static final Sigil DUKE_BUNE = new Sigil("duke_bune", 0xffff73, "e");
    public static final Sigil MARQUIS_RONOVE = new Sigil("marquis/count_ronove", 0xffff73, "e");
    public static final Sigil DUKE_BERITH = new Sigil("duke_berith", 0xffff73, "e");
    public static final Sigil DUKE_ASTAROTH = new Sigil("duke_astaroth", 0xffff73, "e");
    public static final Sigil MARQUIS_FORNEUS = new Sigil("marquis_forneus", 0xffff73, "e");
    public static final Sigil PRESIDENT_FORAS = new Sigil("president_foras", 0xffff73, "e");
    public static final Sigil KING_ASMODAY = new Sigil("king_asmoday", 0xffff73, "e");
    public static final Sigil PRINCE_GAAP = new Sigil("prince_gaap", 0xffff73, "e");
    public static final Sigil COUNT_FURFUR = new Sigil("count_furfur", 0xffff73, "e");
    public static final Sigil MARQUIS_MARCHOSIAS = new Sigil("marquis_marchosias", 0xffff73, "e");
    public static final Sigil PRINCE_STOLAS = new Sigil("prince_stolas", 0xffff73, "e");
    public static final Sigil MARQUIS_PHENEX = new Sigil("marquis_phenex", 0xffff73, "e");
    public static final Sigil COUNT_HALPHAS = new Sigil("count_halphas", 0xffff73, "e");
    public static final Sigil PRESIDENT_MALPHAS = new Sigil("president_malphas", 0xffff73, "e");
    public static final Sigil COUNT_RäUM = new Sigil("count_reum", 0xffff73, "e");
    public static final Sigil DUKE_FOCALOR = new Sigil("duke_focalor", 0xffff73, "e");
    public static final Sigil DUKE_VEPAR = new Sigil("duke_vepar", 0xffff73, "e");
    public static final Sigil MARQUIS_SABNOCK = new Sigil("marquis_sabnock", 0xffff73, "e");
    public static final Sigil MARQUIS_SHAX = new Sigil("marquis_shax", 0xffff73, "e");
    public static final Sigil KING_VINE = new Sigil("king/count_vine", 0xffff73, "e");
    public static final Sigil COUNT_BIFRONS = new Sigil("count_bifrons", 0xffff73, "e");
    public static final Sigil DUKE_VUAL = new Sigil("duke_vual", 0xffff73, "e");
    public static final Sigil PRESIDENT_HAAGENTI = new Sigil("president_haagenti", 0xffff73, "e");
    public static final Sigil DUKE_CROCELL = new Sigil("duke_crocell", 0xffff73, "e");
    public static final Sigil KNIGHT_FURCAS = new Sigil("knight_furcas", 0xffff73, "e");
    public static final Sigil KING_BALAM = new Sigil("king_balam", 0xffff73, "e");
    public static final Sigil DUKE_ALLOCES = new Sigil("duke_alloces", 0xffff73, "e");
    public static final Sigil PRESIDENT_CAIM = new Sigil("president_caim", 0xffff73, "e");
    public static final Sigil DUKE_MURMUR = new Sigil("duke/count_murmur", 0xffff73, "e");
    public static final Sigil PRINCE_OROBAS = new Sigil("prince_orobas", 0xffff73, "e");
    public static final Sigil DUKE_GREMORY = new Sigil("duke_gremory", 0xffff73, "e");
    public static final Sigil PRESIDENT_OSE = new Sigil("president_ose", 0xffff73, "e");
    public static final Sigil PRESIDENT_AMY = new Sigil("president_amy", 0xffff73, "e");
    public static final Sigil MARQUIS_ORIAS = new Sigil("marquis_orias", 0xffff73, "e");
    public static final Sigil DUKE_VAPULA = new Sigil("duke_vapula", 0xffff73, "e");
    public static final Sigil KING_ZAGAN = new Sigil("king_zagan", 0xffff73, "e");
    public static final Sigil PRESIDENT_VALAC = new Sigil("president_valac", 0xffff73, "e");
    public static final Sigil MARQUIS_ANDRAS = new Sigil("marquis_andras", 0xffff73, "e");
    public static final Sigil DUKE_FLAUROS = new Sigil("duke_flauros", 0xffff73, "e");
    public static final Sigil MARQUIS_ANDREALPHUS = new Sigil("marquis_andrealphus", 0xffff73, "e");
    public static final Sigil MARQUIS_KIMARIS = new Sigil("marquis_kimaris", 0xffff73, "e");
    public static final Sigil DUKE_AMDUSIAS = new Sigil("duke_amdusias", 0xffff73, "e");
    public static final Sigil KING_BELIAL = new Sigil("king_belial", 0xffff73, "e");
    public static final Sigil MARQUIS_DECARABIA = new Sigil("marquis_decarabia", 0xffff73, "e");
    public static final Sigil PRINCE_SEERE = new Sigil("prince_seere", 0xffff73, "e");
    public static final Sigil DUKE_DANTALION = new Sigil("duke_dantalion", 0xffff73, "e");
    public static final Sigil COUNT_ANDROMALIUS = new Sigil("count_andromalius", 0xffff73, "e");

    // Kings of the cardinal directions NESW
    // public static final Sigil EGYN = null;    // Aegym/Egion
    // public static final Sigil ORIENS = null;  // Asmodel/Amodeo
    // public static final Sigil AMAYMON = null; // Amaimon/Amoymon
    // public static final Sigil PAIMON = null;  // Paymon

    // primal sigils
    public static final Sigil EARTH = new Sigil("earth", 0xffff73, "e");
    public static final Sigil FIRE = new Sigil("fire", 0xffff73, "e");
    public static final Sigil WIND = new Sigil("wind", 0xffff73, "e");
    public static final Sigil AIR = new Sigil("air", 0xffff73, "e");
    public static final Sigil DARK = new Sigil("chaos", 0xffff73, "e");
    public static final Sigil LIGHT = new Sigil("order", 0xffff73, "e");
}
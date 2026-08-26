package dev.otectus.mcaconversations.compat;

import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

/**
 * A building Townstead has registered with MCA (Townstead spec 5.2).
 *
 * <p>Content tests <em>this</em>, the registered building, never the blocks that make it up. A
 * player who assembles a dock-shaped pile of planks is not standing in a dock until Townstead says
 * so.
 *
 * <p>{@link #type()} carries Townstead's own building type id ({@code dock_l1}, {@code wool_shed},
 * {@code butcher_shop_l2}, and so on); {@link #level()} reads the trailing {@code _lN} where the type
 * has one, so a line can ask for "a dock of at least level 2" without enumerating ids.
 */
public record TownsteadBuildingView(
        boolean present,
        int id,
        int villageId,
        String type,
        int size,
        int centerX,
        int centerY,
        int centerZ,
        int minX,
        int minY,
        int minZ,
        int maxX,
        int maxY,
        int maxZ) {

    /** No registered building here. Spec 5.2 wants a real object rather than a null to branch on. */
    public static final TownsteadBuildingView EMPTY =
            new TownsteadBuildingView(false, -1, -1, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    public BlockPos center() {
        return new BlockPos(centerX, centerY, centerZ);
    }

    public BlockPos min() {
        return new BlockPos(minX, minY, minZ);
    }

    public BlockPos max() {
        return new BlockPos(maxX, maxY, maxZ);
    }

    /**
     * The tier encoded in the type id's {@code _lN} suffix, or {@code 1} for an untiered building.
     * {@code dock_l3} is level 3; {@code pen} is level 1.
     */
    public int level() {
        return levelOf(type);
    }

    /** The type id with any {@code _lN} suffix removed, so all three dock levels share a family. */
    public String family() {
        return familyOf(type);
    }

    /** See {@link #level()}. Static so every caller reads a tier by exactly the same rule. */
    public static int levelOf(String type) {
        String digits = levelSuffix(type);
        return digits == null ? 1 : Integer.parseInt(digits);
    }

    /** See {@link #family()}. */
    public static String familyOf(String type) {
        String digits = levelSuffix(type);
        return digits == null ? type : type.substring(0, type.length() - digits.length() - 2);
    }

    /**
     * The digits of a trailing {@code _lN}, or {@code null} when there is no such suffix. Kept in one
     * place so {@link #level()} and {@link #family()} can never disagree about what a tier looks
     * like. The length check bounds the parse to int range, so a pathological id cannot overflow.
     */
    @Nullable
    private static String levelSuffix(String type) {
        int marker = type.lastIndexOf("_l");
        if (marker <= 0) {
            return null;
        }
        String suffix = type.substring(marker + 2);
        if (suffix.isEmpty() || suffix.length() > 9 || !suffix.chars().allMatch(Character::isDigit)) {
            return null;
        }
        return suffix;
    }
}

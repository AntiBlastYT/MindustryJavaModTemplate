package erek.content.units;

import arc.graphics.Color;
import mindustry.graphics.Pal;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.unit.ErekirUnitType;


public class ErUnitType extends ErekirUnitType{
    public static final Color OColor = Pal.darkOutline;

    public ErUnitType(String name) {
        super(name);

        ammoType = new PowerAmmoType(300);
    }

    @Override
    public void init() {
        super.init();
        float maxWeaponRange = 0;
        for (Weapon weapon : weapons) {
            if (weapon.range() > maxWeaponRange) {
                maxWeaponRange = weapon.range();
            }
        }
        fogRadius = maxWeaponRange / 8;
    }

    public void setRequirements(ItemStack[] stacks) {
        cachedRequirements = stacks;
        totalRequirements = firstRequirements = ItemStack.mult(stacks, 1 / 15f);
    }
}
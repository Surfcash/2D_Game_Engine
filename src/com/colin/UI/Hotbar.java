package com.colin.UI;

import com.colin.CoordinateObject;
import com.colin.Item;

public class Hotbar extends CoordinateObject {

    public static final int BUFFER_FROM_BOTTOM = 50;

    private Slot[] slots = new Slot[Item.ITEM_IDS.length];
    private int selectedSlot;

    public Hotbar() {
        super((getApplet().width / 2F) - ((Slot.SLOT_SIZE * Item.ITEM_IDS.length) / 2F),  getApplet().height - Slot.SLOT_SIZE - BUFFER_FROM_BOTTOM);
        initItems();
    }

    private void initItems() {
        for(int i = 0; i < Item.ITEM_IDS.length; i++) {
            getSlots()[i] = new Slot(getPos().x + (i * Slot.SLOT_SIZE), getPos().y,i, new ItemUI(getPos().x + (i * Slot.SLOT_SIZE) + 4, getPos().y + 4, Item.ITEM_IDS[i]));
        }
    }

    public void update() {

    }

    public void render() {
        for(Slot i : getSlots()) {
            if(i == getSlots()[getSelectedSlotNum()]) {
                i.renderSelected();
            } else {
                i.render();
            }
        }
    }

    public void setSlot(int num) {
        selectedSlot = num;
    }

    public void nextSlot() {
        if(getSelectedSlotNum() < getSlots().length - 1) {
            setSelectedSlot(getSelectedSlotNum() + 1);
        } else {
            setSelectedSlot(0);
        }
    }

    public void previousSlot() {
        if(getSelectedSlotNum() > 0) {
            setSelectedSlot(getSelectedSlotNum() - 1);
        } else {
            setSelectedSlot(getSlots().length - 1);
        }
    }

    public Slot[] getSlots() {
        return slots;
    }

    public int getSelectedSlotNum() {
        return selectedSlot;
    }

    public Slot getSelectedSlot() {
        return getSlots()[getSelectedSlotNum()];
    }

    public void setSelectedSlot(int num) {
        selectedSlot = num;
    }
}

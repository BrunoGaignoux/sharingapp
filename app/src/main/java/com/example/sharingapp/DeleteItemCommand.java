package com.example.sharingapp;

import java.util.concurrent.ExecutionException;

/**
 * Command to delete an item
 */
public class DeleteItemCommand extends Command {

    private Item item;

    public DeleteItemCommand(Item item) {
        this.item = item;
    }

    // Delete the item remotely from server
    public void execute() {
        ElasticSearchManager.RemoveItemTask remove_item_task = new ElasticSearchManager.RemoveItemTask();
        remove_item_task.execute(item);

        try {
            if(remove_item_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}

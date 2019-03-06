package datastructures;

public class HashTable<K, V> {

    private int INITIAL_SIZE = 16;
    private HashEntry[] data; // LinkedList

    class HashEntry<EK, EV> {
        EK key;
        EV value;
        HashEntry<EK, EV> next;

        public HashEntry(EK key, EV value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public HashTable() {
        data = new HashEntry[INITIAL_SIZE];
    }

    public void put(K key, V value) {

        // Get the index
        int index = getIndex(key);

        // Create the linked list entry
        HashEntry<K, V> entry = new HashEntry<>(key, value);

        // If no entry there - add it
        if (data[index] == null) {
            data[index] = entry;
        } else {
            // Else handle collision by adding to end of linked list
            HashEntry<K, V> entries = data[index];

            while (entries.next != null) {
                entries = entries.next;
            }

            // Add our new entry there
            entries.next = entry;
        }
    }

    public V get(K key) {

        // Get the index
        int index = getIndex(key);

        // Get the current list of entries
        HashEntry<K, V> entries = data[index];

        // if we have existing entries against this key...
        if (entries != null) {

            // else walk chain until find a match
            while (!entries.key.equals(key) && entries.next != null) {
                entries = entries.next;
            }

            // then return it
            return entries.value;
        }

        // it we have no entries against this key...
        return null;
    }

    private int getIndex(K key) {
        // Get the hash code
        int hashCode = key.hashCode();
        System.out.println("hashCode = " + hashCode);

        // Convert to index
//        int index = hashCode % INITIAL_SIZE;
        // avoid minus in index
        int index = (hashCode & 0x7fffffff) % INITIAL_SIZE;

        // Hack to force collision for testing
        if (key.equals("John Smith") || key.equals("Sandra Dee")) {
            index = 4;
        }

        System.out.println("index = " + index);

        return index;
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashTable.HashEntry entry : data) {
            if(entry == null) {
                continue;
            }
            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashTable.HashEntry temp = entry.next;
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

}
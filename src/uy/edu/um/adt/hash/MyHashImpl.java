package uy.edu.um.adt.hash;

import java.util.ArrayList;

public class MyHashImpl<K,V> implements MyHash<K, V> {

    private ArrayList<HashNode<K, V>> myArray;
    private int maxBuckets;
    private int size;

    public MyHashImpl() {
        myArray = new ArrayList<>();
        maxBuckets = 13; //numero inicial de buckets(
        size = 0;

        for (int i = 0; i < maxBuckets; i++) {
            myArray.add(null);
        }
    }

    private int getBucketPosition(K key) {
        int hashPosition = key.hashCode();
        return Math.abs(hashPosition % maxBuckets);
    }

    @Override
    public void put(K key, V value) throws FullArrayException {
        HashNode<K, V> newHash = new HashNode<>(key, value);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        while (myArray.get(bucket) != null && !myArray.get(bucket).equals(newHash)) {
            bucket = (bucket + 1) % maxBuckets;
            if (bucket == initialBucket) {
                throw new FullArrayException();
            }
        }
        if (myArray.get(bucket) != null && myArray.get(bucket).equals(newHash)) {
            System.out.printf("value " + myArray.get(bucket).getValue() + " changed to " + newHash.getValue() + "\n");
            myArray.get(bucket).setValue(value);
        } else {
            myArray.set(bucket, newHash);
            System.out.printf(newHash.getValue() + " added " + "\n");
            size++;
        }

        if ((1.0 * size) / maxBuckets >= 0.7) {
            ArrayList<HashNode<K, V>> temp = myArray;
            myArray = new ArrayList<>();
            maxBuckets = 2 * maxBuckets;
            size = 0;
            for (int i = 0; i < maxBuckets; i++) {
                myArray.add(null);
            }
            for (HashNode<K, V> node : temp) {
                if (node != null) {
                    put(node.getKey(), node.getValue());
                }
            }
        }
    }

    @Override
    public boolean contains(K key) {
        HashNode<K, V> newHash = new HashNode<>(key, null);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        while (myArray.get(bucket) != null && !myArray.get(bucket).equals(newHash)) {
            bucket = (bucket + 1) % maxBuckets;
            if (bucket == initialBucket) {
                return false;
            }
        }
        if (myArray.get(bucket) == null){
            return false;
        }
        else {
            return myArray.get(bucket).equals(newHash);
        }
    }

    @Override
    public void remove(K key) throws EntidadNoExiste, FullArrayException {
        HashNode<K, V> newHash = new HashNode<>(key, null);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        if (myArray.get(bucket) != null) {
            while (myArray.get(bucket) != null && !myArray.get(bucket).equals(newHash)) {
                bucket = (bucket + 1) % maxBuckets;
                if (bucket == initialBucket) {
                    throw new EntidadNoExiste();
                }
            }
            myArray.remove(bucket);
            ArrayList<HashNode<K, V>> temp = myArray;
            myArray = new ArrayList<>();
            size = 0;
            for (int i = 0; i < maxBuckets; i++) {
                myArray.add(null);
            }
            for (HashNode<K, V> node : temp) {
                if (node != null) {
                    put(node.getKey(), node.getValue());
                }

            }
        } else {
            System.out.println("El bucket asignado es nulo");
        }
    }


    @Override
    public void resize(int newMaxBuckets) throws FullArrayException {
        ArrayList<HashNode<K, V>> temp = myArray;
        myArray = new ArrayList<>();
        maxBuckets = newMaxBuckets;
        size = 0;
        for (int i = 0; i < maxBuckets; i++) {
            myArray.add(null);
        }
        for (HashNode<K, V> node : temp) {
            if (node != null) {
                put(node.getKey(), node.getValue());
            }
        }
    }
}

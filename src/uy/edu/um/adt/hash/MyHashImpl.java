package uy.edu.um.adt.hash;

import java.util.ArrayList;

public class MyHashImpl<K,V> implements MyHash<K, V> {

    private ArrayList<HashNode<K,V>> myArray;
    private int maxBuckets;
    private int size;

    public MyHashImpl() {
        myArray = new ArrayList<>();
        maxBuckets = 13; //numero inicial de buckets(
        size = 0;

        for (int i = 0; i < maxBuckets; i++){
            myArray.add(null);
        }
    }

    private int getBucketPosition(K key) {
        int hashPosition = key.hashCode();
        return Math.abs(hashPosition % maxBuckets);
    }

    @Override
    public void put(K key, V value) throws FullArrayException {
        HashNode<K,V> newHash = new HashNode<>(key,value);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        while ( myArray.get(bucket) != null && !myArray.get(bucket).equals(key)) {
            bucket = (bucket + 1) % maxBuckets;
            if (bucket == initialBucket) {
                throw new FullArrayException();
            }
        }
        if(myArray.get(bucket).equals(key)) {
            myArray.get(bucket).setValue(value);
        } else {
            myArray.set(bucket, newHash);
            size++;
        }
    }

    @Override
    public boolean contains(K key) throws FullArrayException {
        HashNode<K,V> newHash = new HashNode<>(key,null);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        while ( myArray.get(bucket) != null && !myArray.get(bucket).equals(key)) {
            bucket = (bucket + 1) % maxBuckets;
            if (bucket == initialBucket) {
                throw new FullArrayException();
            }
        }
        return myArray.get(bucket).equals(newHash);
    }

    @Override
    public void remove(K key) throws FullArrayException {
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        while (!myArray.get(bucket).equals(key)) {
            bucket = (bucket + 1) % maxBuckets;
            if (bucket == initialBucket) {
                throw new FullArrayException();
            }
        }
        myArray.remove(bucket);
    }
}

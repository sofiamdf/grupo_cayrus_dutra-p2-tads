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
        /*
        if ((1.0 * size) / maxBuckets >= 0.7) {
            List<HashNode<K, V>> temp = myArray;
            myArray = new ArrayList<>();
            maxBuckets = 2 * maxBuckets;
            size = 0;
            for (int i = 0; i < maxBuckets; i++) {
                myArray.add(null);
            }
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.getKey(), headNode.getValue()); // no tenemos headnode!
                    headNode = headNode.getNext();
                }
            }

         */
    }

    @Override
    public boolean contains(K key) {
        HashNode<K,V> newHash = new HashNode<>(key, null);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        while (myArray.get(bucket) != null && !myArray.get(bucket).equals(newHash)) {
            bucket = (bucket + 1) % maxBuckets;
            if (bucket == initialBucket) {
                return false;
            }
        }
        return myArray.get(bucket).equals(newHash);
    }

    @Override
    public void remove(K key) throws EntidadNoExiste {
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        if (myArray.get(bucket) != null) {
            while (!myArray.get(bucket).equals(key)) {
                bucket = (bucket + 1) % maxBuckets;
                if (bucket == initialBucket) {
                    throw new EntidadNoExiste();
                }
            }
            myArray.remove(bucket);
            size--;
            int nextBucket = bucket + 1;
            int secondInitialBucket = bucket;
            while (getBucketPosition((myArray.get(nextBucket).getKey())) == initialBucket){
                myArray.set(bucket, myArray.get(nextBucket));
                nextBucket = (nextBucket + 1) % maxBuckets;
                bucket = (bucket +1) % maxBuckets;
                if (nextBucket == secondInitialBucket) {
                    throw new EntidadNoExiste();
                }

            }
        } else {
            System.out.println("El bucket asignado es nulo");
        }
    }
    /*
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        int originalIndex = bucketIndex;

        // 1. Encontrar el nodo con la clave especificada
        while (bucketArray[bucketIndex] != null) {
            if (bucketArray[bucketIndex].getKey().equals(key)) {
                // 2. Guardar el valor para devolverlo
                V value = bucketArray[bucketIndex].getValue();

                // 3. Eliminar el nodo encontrado
                bucketArray[bucketIndex] = null;
                size--;

                // 4. Reorganizar los nodos desplazados por colisiones
                bucketIndex = (bucketIndex + 1) % numBuckets;
                while (bucketArray[bucketIndex] != null) {
                    HashNode<K, V> nodeToRehash = bucketArray[bucketIndex];
                    bucketArray[bucketIndex] = null;
                    size--;
                    put(nodeToRehash.getKey(), nodeToRehash.getValue());
                    bucketIndex = (bucketIndex + 1) % numBuckets;
                }

                return value;
            }
            bucketIndex = (bucketIndex + 1) % numBuckets;
            if (bucketIndex == originalIndex) {
                // Hemos dado la vuelta completa y no encontramos la clave
                return null;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }


    @Override
    public void resize (lo puse comentado en el push con la
            forma de chatgpt, despues lo miramos)
    */
}

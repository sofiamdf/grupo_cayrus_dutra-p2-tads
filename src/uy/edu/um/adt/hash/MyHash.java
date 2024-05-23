package uy.edu.um.adt.hash;

public interface MyHash<K,V> {
    public void put(K key, V value) throws FullArrayException;
    public boolean contains(K key) throws EntidadNoExiste;
    public void remove(K key) throws EntidadNoExiste;
}

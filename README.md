# Tugas 10: Advanced RecyclerView

Proyek ini merupakan tugas mata kuliah Pemrograman Perangkat Bergerak yang mengimplementasikan pembaruan data secara efisien pada RecyclerView menggunakan arsitektur modern di Android Studio.

## Identitas Mahasiswa
* **Nama:** Abinaya Azhar Probo Kusumo
* **NIM:** 452024811054
* **Kelas:** Teknik Informatika - A (Angkatan 2024)

---

## Analisis Efisiensi: RecyclerView.Adapter vs ListAdapter

Berdasarkan materi pada Lesson 10, berikut adalah analisis perbedaan efisiensi manipulasi data antara kedua jenis adapter:

1. **RecyclerView.Adapter Standar (`notifyDataSetChanged()`):**
   * **Mekanisme:** Bersifat destruktif. Memaksa RecyclerView menghapus seluruh ViewHolder yang sedang terlihat di layar dan menggambarnya ulang dari nol (*full redraw*), meskipun data yang berubah hanya satu baris.
   * **Dampak Performa:** Membutuhkan tindakan kalkulasi yang besar dan boros resource (*costly and wasteful*). Hal ini memicu terjadinya *frame drop* (lag) jika daftar data berukuran besar, serta hilangnya animasi transisi visual.

2. **ListAdapter + DiffUtil:**
   * **Mekanisme:** Menggunakan algoritma Myers di *Background Thread* untuk menghitung jumlah minimal pembaruan data secara spesifik ($O(N + D)$).
   * **Dampak Performa:** Sangat efisien karena *Main UI Thread* tidak dibebani proses kalkulasi berat. RecyclerView hanya akan memperbarui komponen komponen yang benar-benar berubah (*add, remove, update*), menghasilkan rendering yang mulus dan animasi transisi yang halus.

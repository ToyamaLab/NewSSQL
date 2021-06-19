// Copyright (C) 2011 Júlio Hoffimann.

// Use, modification and distribution is subject to the Boost Software
// License, Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

// Message Passing Interface 1.1 -- Section 4.6. Scatterv
#ifndef BOOST_MPI_SCATTERV_HPP
#define BOOST_MPI_SCATTERV_HPP

<<<<<<< HEAD
#include <boost/mpi/exception.hpp>
#include <boost/mpi/datatype.hpp>
#include <vector>
#include <boost/mpi/packed_oarchive.hpp>
#include <boost/mpi/packed_iarchive.hpp>
#include <boost/mpi/detail/point_to_point.hpp>
#include <boost/mpi/communicator.hpp>
#include <boost/mpi/environment.hpp>
#include <boost/assert.hpp>
=======
#include <boost/scoped_array.hpp>
#include <boost/mpi/collectives/scatter.hpp>
#include <boost/mpi/detail/offsets.hpp>
#include <boost/mpi/detail/antiques.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost { namespace mpi {

namespace detail {
<<<<<<< HEAD
  // We're scattering from the root for a type that has an associated MPI
  // datatype, so we'll use MPI_Scatterv to do all of the work.
  template<typename T>
  void
  scatterv_impl(const communicator& comm, const T* in_values, const int* sizes,
                const int* displs, T* out_values, int out_size, int root, mpl::true_)
  {
    MPI_Datatype type = get_mpi_datatype<T>(*in_values);
    BOOST_MPI_CHECK_RESULT(MPI_Scatterv,
                           (const_cast<T*>(in_values), const_cast<int*>(sizes),
                            const_cast<int*>(displs), type,
                            out_values, out_size, type, root, comm));
  }

  // We're scattering from a non-root for a type that has an associated MPI
  // datatype, so we'll use MPI_Scatterv to do all of the work.
  template<typename T>
  void
  scatterv_impl(const communicator& comm, T* out_values, int out_size, int root, 
               mpl::true_)
  {
    MPI_Datatype type = get_mpi_datatype<T>(*out_values);
    BOOST_MPI_CHECK_RESULT(MPI_Scatterv,
                           (0, 0, 0, type,
                            out_values, out_size, type,
                            root, comm));
  }

  // We're scattering from the root for a type that does not have an
  // associated MPI datatype, so we'll need to serialize
  // it. Unfortunately, this means that we cannot use MPI_Scatterv, so
  // we'll just have the root send individual messages to the other
  // processes.
  template<typename T>
  void
  scatterv_impl(const communicator& comm, const T* in_values, const int* sizes,
                const int* displs, T* out_values, int out_size, int root, mpl::false_)
  {
    int tag = environment::collectives_tag();
    int nprocs = comm.size();

    for (int dest = 0; dest < nprocs; ++dest) {
      if (dest == root) {
        // Our own values will never be transmitted: just copy them.
        std::copy(in_values + displs[dest],
                  in_values + displs[dest] + out_size, out_values);
      } else {
        // Send archive
        packed_oarchive oa(comm);
        for (int i = 0; i < sizes[dest]; ++i)
          oa << in_values[ displs[dest] + i ];
        detail::packed_archive_send(comm, dest, tag, oa);
      }
    }
  }

  // We're scattering to a non-root for a type that does not have an
  // associated MPI datatype, so we'll need to de-serialize
  // it. Unfortunately, this means that we cannot use MPI_Scatterv, so
  // we'll just have all of the non-root nodes send individual
  // messages to the root.
  template<typename T>
  void
  scatterv_impl(const communicator& comm, T* out_values, int out_size, int root, 
               mpl::false_)
  {
    int tag = environment::collectives_tag();

    packed_iarchive ia(comm);
    MPI_Status status;
    detail::packed_archive_recv(comm, root, tag, ia, status);
    for (int i = 0; i < out_size; ++i)
      ia >> out_values[i];
  }
=======

//////////////////////////////////////////////
/// Implementation for MPI primitive types ///
//////////////////////////////////////////////

// We're scattering from the root for a type that has an associated MPI
// datatype, so we'll use MPI_Scatterv to do all of the work.
template<typename T>
void
scatterv_impl(const communicator& comm, const T* in_values, T* out_values, int out_size,
              const int* sizes, const int* displs, int root, mpl::true_)
{
  assert(!sizes || out_size == sizes[comm.rank()]);
  assert(bool(sizes) == bool(in_values));
  
  scoped_array<int> new_offsets_mem(make_offsets(comm, sizes, displs, root));
  if (new_offsets_mem) displs = new_offsets_mem.get();
  MPI_Datatype type = get_mpi_datatype<T>(*in_values);
  BOOST_MPI_CHECK_RESULT(MPI_Scatterv,
                         (const_cast<T*>(in_values), const_cast<int*>(sizes),
                          const_cast<int*>(displs), type,
                          out_values, out_size, type, root, comm));
}

// We're scattering from a non-root for a type that has an associated MPI
// datatype, so we'll use MPI_Scatterv to do all of the work.
template<typename T>
void
scatterv_impl(const communicator& comm, T* out_values, int out_size, int root, 
              mpl::true_ is_mpi_type)
{
  scatterv_impl(comm, (T const*)0, out_values, out_size, 
                (const int*)0, (const int*)0, root, is_mpi_type);
}

//////////////////////////////////////////////////
/// Implementation for non MPI primitive types ///
//////////////////////////////////////////////////

// We're scattering from the root for a type that does not have an
// associated MPI datatype, so we'll need to serialize it.
template<typename T>
void
scatterv_impl(const communicator& comm, const T* in_values, T* out_values, int out_size,
              int const* sizes, int const* displs, int root, mpl::false_)
{
  packed_oarchive::buffer_type sendbuf;
  bool is_root = comm.rank() == root;
  int nproc = comm.size();
  std::vector<int> archsizes;
  if (is_root) {
    assert(out_size == sizes[comm.rank()]);
    archsizes.resize(nproc);
    std::vector<int> skipped;
    if (displs) {
      skipped.resize(nproc);
      offsets2skipped(sizes, displs, c_data(skipped), nproc);
      displs = c_data(skipped);
    }
    fill_scatter_sendbuf(comm, in_values, sizes, (int const*)0, sendbuf, archsizes);
  }
  dispatch_scatter_sendbuf(comm, sendbuf, archsizes, (T const*)0, out_values, out_size, root);
}

// We're scattering to a non-root for a type that does not have an
// associated MPI datatype. input data not needed.
// it.
template<typename T>
void
scatterv_impl(const communicator& comm, T* out_values, int n, int root, 
              mpl::false_ isnt_mpi_type)
{
  assert(root != comm.rank());
  scatterv_impl(comm, (T const*)0, out_values, n, (int const*)0, (int const*)0, root, isnt_mpi_type);
}

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
} // end namespace detail

template<typename T>
void
scatterv(const communicator& comm, const T* in_values,
         const std::vector<int>& sizes, const std::vector<int>& displs,
         T* out_values, int out_size, int root)
{
<<<<<<< HEAD
  int rank = comm.rank();
  if (rank == root)
    detail::scatterv_impl(comm, in_values, &sizes[0], &displs[0],
                          out_values, out_size, root, is_mpi_datatype<T>());
  else
    detail::scatterv_impl(comm, out_values, out_size, root,
                          is_mpi_datatype<T>());
=======
  using detail::c_data;
  detail::scatterv_impl(comm, in_values, out_values, out_size, c_data(sizes), c_data(displs), 
                root, is_mpi_datatype<T>());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<typename T>
void
scatterv(const communicator& comm, const std::vector<T>& in_values, 
         const std::vector<int>& sizes, const std::vector<int>& displs,
         T* out_values, int out_size, int root)
{
<<<<<<< HEAD
  if (comm.rank() == root)
    ::boost::mpi::scatterv(comm, &in_values[0], sizes, displs,
                           out_values, out_size, root);
  else
    ::boost::mpi::scatterv(comm, static_cast<const T*>(0), sizes, displs,
                           out_values, out_size, root);
=======
  using detail::c_data;
  ::boost::mpi::scatterv(comm, c_data(in_values), sizes, displs,
                         out_values, out_size, root);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<typename T>
void scatterv(const communicator& comm, T* out_values, int out_size, int root)
{
  BOOST_ASSERT(comm.rank() != root);
  detail::scatterv_impl(comm, out_values, out_size, root, is_mpi_datatype<T>());
}

///////////////////////
// common use versions
///////////////////////
template<typename T>
void
scatterv(const communicator& comm, const T* in_values,
         const std::vector<int>& sizes, T* out_values, int root)
{
<<<<<<< HEAD
  int nprocs = comm.size();
  int myrank = comm.rank();

  std::vector<int> displs(nprocs);
  for (int rank = 0, aux = 0; rank < nprocs; ++rank) {
    displs[rank] = aux;
    aux += sizes[rank];
  }
  ::boost::mpi::scatterv(comm, in_values, sizes, displs, out_values,
                         sizes[myrank], root);
=======
  using detail::c_data;
  detail::scatterv_impl(comm, in_values, out_values, sizes[comm.rank()], 
                        c_data(sizes), (int const*)0,
                        root, is_mpi_datatype<T>());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<typename T>
void
scatterv(const communicator& comm, const std::vector<T>& in_values,
         const std::vector<int>& sizes, T* out_values, int root)
{
  ::boost::mpi::scatterv(comm, &in_values[0], sizes, out_values, root);
}

<<<<<<< HEAD
=======
template<typename T>
void
scatterv(const communicator& comm, const T* in_values,
         T* out_values, int n, int root)
{
  detail::scatterv_impl(comm, in_values, out_values, n, (int const*)0, (int const*)0,
                root, is_mpi_datatype<T>());
}

template<typename T>
void
scatterv(const communicator& comm, const std::vector<T>& in_values,
         T* out_values, int out_size, int root)
{
  ::boost::mpi::scatterv(comm, &in_values[0], out_values, out_size, root);
}

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
} } // end namespace boost::mpi

#endif // BOOST_MPI_SCATTERV_HPP

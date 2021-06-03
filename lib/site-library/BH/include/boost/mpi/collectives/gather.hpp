// Copyright (C) 2005, 2006 Douglas Gregor.

// Use, modification and distribution is subject to the Boost Software
// License, Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

// Message Passing Interface 1.1 -- Section 4.5. Gather
#ifndef BOOST_MPI_GATHER_HPP
#define BOOST_MPI_GATHER_HPP

<<<<<<< HEAD
=======
#include <cassert>
#include <cstddef>
#include <numeric>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/mpi/exception.hpp>
#include <boost/mpi/datatype.hpp>
#include <vector>
#include <boost/mpi/packed_oarchive.hpp>
#include <boost/mpi/packed_iarchive.hpp>
#include <boost/mpi/detail/point_to_point.hpp>
#include <boost/mpi/communicator.hpp>
#include <boost/mpi/environment.hpp>
<<<<<<< HEAD
=======
#include <boost/mpi/detail/offsets.hpp>
#include <boost/mpi/detail/antiques.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/assert.hpp>

namespace boost { namespace mpi {

namespace detail {
<<<<<<< HEAD
  // We're gathering at the root for a type that has an associated MPI
  // datatype, so we'll use MPI_Gather to do all of the work.
  template<typename T>
  void
  gather_impl(const communicator& comm, const T* in_values, int n, 
              T* out_values, int root, mpl::true_)
  {
    MPI_Datatype type = get_mpi_datatype<T>(*in_values);
    BOOST_MPI_CHECK_RESULT(MPI_Gather,
                           (const_cast<T*>(in_values), n, type,
                            out_values, n, type, root, comm));
  }

  // We're gathering from a non-root for a type that has an associated MPI
  // datatype, so we'll use MPI_Gather to do all of the work.
  template<typename T>
  void
  gather_impl(const communicator& comm, const T* in_values, int n, int root, 
              mpl::true_)
  {
    MPI_Datatype type = get_mpi_datatype<T>(*in_values);
    BOOST_MPI_CHECK_RESULT(MPI_Gather,
                           (const_cast<T*>(in_values), n, type,
                            0, n, type, root, comm));
  }

  // We're gathering at the root for a type that does not have an
  // associated MPI datatype, so we'll need to serialize
  // it. Unfortunately, this means that we cannot use MPI_Gather, so
  // we'll just have all of the non-root nodes send individual
  // messages to the root.
  template<typename T>
  void
  gather_impl(const communicator& comm, const T* in_values, int n, 
              T* out_values, int root, mpl::false_)
  {
    int tag = environment::collectives_tag();
    int size = comm.size();

    for (int src = 0; src < size; ++src) {
      if (src == root)
        std::copy(in_values, in_values + n, out_values + n * src);
      else
        comm.recv(src, tag, out_values + n * src, n);
    }
  }

  // We're gathering at a non-root for a type that does not have an
  // associated MPI datatype, so we'll need to serialize
  // it. Unfortunately, this means that we cannot use MPI_Gather, so
  // we'll just have all of the non-root nodes send individual
  // messages to the root.
  template<typename T>
  void
  gather_impl(const communicator& comm, const T* in_values, int n, int root, 
              mpl::false_)
  {
    int tag = environment::collectives_tag();
    comm.send(root, tag, in_values, n);
  }
=======
// We're gathering at the root for a type that has an associated MPI
// datatype, so we'll use MPI_Gather to do all of the work.
template<typename T>
void
gather_impl(const communicator& comm, const T* in_values, int n, 
            T* out_values, int root, mpl::true_)
{
  MPI_Datatype type = get_mpi_datatype<T>(*in_values);
  BOOST_MPI_CHECK_RESULT(MPI_Gather,
                         (const_cast<T*>(in_values), n, type,
                          out_values, n, type, root, comm));
}

// We're gathering from a non-root for a type that has an associated MPI
// datatype, so we'll use MPI_Gather to do all of the work.
template<typename T>
void
gather_impl(const communicator& comm, const T* in_values, int n, int root, 
            mpl::true_ is_mpi_type)
{
  assert(comm.rank() != root);
  gather_impl(comm, in_values, n, (T*)0, root, is_mpi_type);
}

// We're gathering at the root for a type that does not have an
// associated MPI datatype, so we'll need to serialize
// it.
template<typename T>
void
gather_impl(const communicator& comm, const T* in_values, int n, T* out_values, 
            int const* nslot, int const* nskip, int root, mpl::false_)
{
  int nproc = comm.size();
  // first, gather all size, these size can be different for
  // each process
  packed_oarchive oa(comm);
  for (int i = 0; i < n; ++i) {
    oa << in_values[i];
  }
  bool is_root = comm.rank() == root;
  std::vector<int> oasizes(is_root ? nproc : 0);
  int oasize = oa.size();
  BOOST_MPI_CHECK_RESULT(MPI_Gather,
                         (&oasize, 1, MPI_INT,
                          c_data(oasizes), 1, MPI_INT, 
                          root, MPI_Comm(comm)));
  // Gather the archives, which can be of different sizes, so
  // we need to use gatherv.
  // Everything is contiguous (in the transmitted archive), so 
  // the offsets can be deduced from the collected sizes.
  std::vector<int> offsets;
  if (is_root) sizes2offsets(oasizes, offsets);
  packed_iarchive::buffer_type recv_buffer(is_root ? std::accumulate(oasizes.begin(), oasizes.end(), 0) : 0);
  BOOST_MPI_CHECK_RESULT(MPI_Gatherv,
                         (const_cast<void*>(oa.address()), int(oa.size()), MPI_BYTE,
                          c_data(recv_buffer), c_data(oasizes), c_data(offsets), MPI_BYTE, 
                          root, MPI_Comm(comm)));
  if (is_root) {
    for (int src = 0; src < nproc; ++src) {
      // handle variadic case
      int nb = nslot ? nslot[src] : n;
      int skip = nskip ? nskip[src] : 0;
      std::advance(out_values, skip);
      if (src == root) {
        BOOST_ASSERT(nb == n);
        for (int i = 0; i < nb; ++i) {
          *out_values++ = *in_values++;
        }
      } else {
        packed_iarchive ia(comm,  recv_buffer, boost::archive::no_header, offsets[src]);
        for (int i = 0; i < nb; ++i) {
          ia >> *out_values++;
        }
      }
    }
  }
}

// We're gathering at a non-root for a type that does not have an
// associated MPI datatype, so we'll need to serialize
// it.
template<typename T>
void
gather_impl(const communicator& comm, const T* in_values, int n, T* out_values,int root, 
            mpl::false_ is_mpi_type)
{
  gather_impl(comm, in_values, n, out_values, (int const*)0, (int const*)0, root, is_mpi_type);
}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
} // end namespace detail

template<typename T>
void
gather(const communicator& comm, const T& in_value, T* out_values, int root)
{
<<<<<<< HEAD
  if (comm.rank() == root)
    detail::gather_impl(comm, &in_value, 1, out_values, root, 
                        is_mpi_datatype<T>());
  else
    detail::gather_impl(comm, &in_value, 1, root, is_mpi_datatype<T>());
=======
  BOOST_ASSERT(out_values || (comm.rank() != root));
  detail::gather_impl(comm, &in_value, 1, out_values, root, is_mpi_datatype<T>());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<typename T>
void gather(const communicator& comm, const T& in_value, int root)
{
  BOOST_ASSERT(comm.rank() != root);
<<<<<<< HEAD
  detail::gather_impl(comm, &in_value, 1, root, is_mpi_datatype<T>());
=======
  detail::gather_impl(comm, &in_value, 1, (T*)0, root, is_mpi_datatype<T>());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<typename T>
void
gather(const communicator& comm, const T& in_value, std::vector<T>& out_values,
       int root)
{
<<<<<<< HEAD
  if (comm.rank() == root) {
    out_values.resize(comm.size());
    ::boost::mpi::gather(comm, in_value, &out_values[0], root);
  } else {
    ::boost::mpi::gather(comm, in_value, root);
  }
=======
  using detail::c_data;
  if (comm.rank() == root) {
    out_values.resize(comm.size());
  }
  ::boost::mpi::gather(comm, in_value, c_data(out_values), root);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<typename T>
void
gather(const communicator& comm, const T* in_values, int n, T* out_values, 
       int root)
{
<<<<<<< HEAD
  if (comm.rank() == root)
    detail::gather_impl(comm, in_values, n, out_values, root, 
                        is_mpi_datatype<T>());
  else
    detail::gather_impl(comm, in_values, n, root, is_mpi_datatype<T>());
=======
  detail::gather_impl(comm, in_values, n, out_values, root, 
                      is_mpi_datatype<T>());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<typename T>
void
gather(const communicator& comm, const T* in_values, int n, 
       std::vector<T>& out_values, int root)
{
  if (comm.rank() == root) {
    out_values.resize(comm.size() * n);
<<<<<<< HEAD
    ::boost::mpi::gather(comm, in_values, n, &out_values[0], root);
  } 
  else
    ::boost::mpi::gather(comm, in_values, n, root);
=======
  }
  ::boost::mpi::gather(comm, in_values, n, out_values.data(), root);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<typename T>
void gather(const communicator& comm, const T* in_values, int n, int root)
{
  BOOST_ASSERT(comm.rank() != root);
  detail::gather_impl(comm, in_values, n, root, is_mpi_datatype<T>());
}


} } // end namespace boost::mpi

#endif // BOOST_MPI_GATHER_HPP

//
// detail/thread_info_base.hpp
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
<<<<<<< HEAD
// Copyright (c) 2003-2017 Christopher M. Kohlhoff (chris at kohlhoff dot com)
=======
// Copyright (c) 2003-2019 Christopher M. Kohlhoff (chris at kohlhoff dot com)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
//
// Distributed under the Boost Software License, Version 1.0. (See accompanying
// file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
//

#ifndef BOOST_ASIO_DETAIL_THREAD_INFO_BASE_HPP
#define BOOST_ASIO_DETAIL_THREAD_INFO_BASE_HPP

#if defined(_MSC_VER) && (_MSC_VER >= 1200)
# pragma once
#endif // defined(_MSC_VER) && (_MSC_VER >= 1200)

#include <climits>
#include <cstddef>
#include <boost/asio/detail/noncopyable.hpp>

#include <boost/asio/detail/push_options.hpp>

namespace boost {
namespace asio {
namespace detail {

class thread_info_base
  : private noncopyable
{
public:
<<<<<<< HEAD
  thread_info_base()
    : reusable_memory_(0)
  {
=======
  struct default_tag
  {
    enum { mem_index = 0 };
  };

  struct awaitable_frame_tag
  {
    enum { mem_index = 1 };
  };

  struct executor_function_tag
  {
    enum { mem_index = 2 };
  };

  thread_info_base()
  {
    for (int i = 0; i < max_mem_index; ++i)
      reusable_memory_[i] = 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  }

  ~thread_info_base()
  {
<<<<<<< HEAD
    if (reusable_memory_)
      ::operator delete(reusable_memory_);
=======
    for (int i = 0; i < max_mem_index; ++i)
      if (reusable_memory_[i])
        ::operator delete(reusable_memory_[i]);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  }

  static void* allocate(thread_info_base* this_thread, std::size_t size)
  {
<<<<<<< HEAD
    std::size_t chunks = (size + chunk_size - 1) / chunk_size;

    if (this_thread && this_thread->reusable_memory_)
    {
      void* const pointer = this_thread->reusable_memory_;
      this_thread->reusable_memory_ = 0;
=======
    return allocate(default_tag(), this_thread, size);
  }

  static void deallocate(thread_info_base* this_thread,
      void* pointer, std::size_t size)
  {
    deallocate(default_tag(), this_thread, pointer, size);
  }

  template <typename Purpose>
  static void* allocate(Purpose, thread_info_base* this_thread,
      std::size_t size)
  {
    std::size_t chunks = (size + chunk_size - 1) / chunk_size;

    if (this_thread && this_thread->reusable_memory_[Purpose::mem_index])
    {
      void* const pointer = this_thread->reusable_memory_[Purpose::mem_index];
      this_thread->reusable_memory_[Purpose::mem_index] = 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

      unsigned char* const mem = static_cast<unsigned char*>(pointer);
      if (static_cast<std::size_t>(mem[0]) >= chunks)
      {
        mem[size] = mem[0];
        return pointer;
      }

      ::operator delete(pointer);
    }

    void* const pointer = ::operator new(chunks * chunk_size + 1);
    unsigned char* const mem = static_cast<unsigned char*>(pointer);
    mem[size] = (chunks <= UCHAR_MAX) ? static_cast<unsigned char>(chunks) : 0;
    return pointer;
  }

<<<<<<< HEAD
  static void deallocate(thread_info_base* this_thread,
=======
  template <typename Purpose>
  static void deallocate(Purpose, thread_info_base* this_thread,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      void* pointer, std::size_t size)
  {
    if (size <= chunk_size * UCHAR_MAX)
    {
<<<<<<< HEAD
      if (this_thread && this_thread->reusable_memory_ == 0)
      {
        unsigned char* const mem = static_cast<unsigned char*>(pointer);
        mem[0] = mem[size];
        this_thread->reusable_memory_ = pointer;
=======
      if (this_thread && this_thread->reusable_memory_[Purpose::mem_index] == 0)
      {
        unsigned char* const mem = static_cast<unsigned char*>(pointer);
        mem[0] = mem[size];
        this_thread->reusable_memory_[Purpose::mem_index] = pointer;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        return;
      }
    }

    ::operator delete(pointer);
  }

private:
  enum { chunk_size = 4 };
<<<<<<< HEAD
  void* reusable_memory_;
=======
  enum { max_mem_index = 3 };
  void* reusable_memory_[max_mem_index];
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

} // namespace detail
} // namespace asio
} // namespace boost

#include <boost/asio/detail/pop_options.hpp>

#endif // BOOST_ASIO_DETAIL_THREAD_INFO_BASE_HPP

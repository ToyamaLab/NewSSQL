//
// detail/recycling_allocator.hpp
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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

#ifndef BOOST_ASIO_DETAIL_RECYCLING_ALLOCATOR_HPP
#define BOOST_ASIO_DETAIL_RECYCLING_ALLOCATOR_HPP

#if defined(_MSC_VER) && (_MSC_VER >= 1200)
# pragma once
#endif // defined(_MSC_VER) && (_MSC_VER >= 1200)

#include <boost/asio/detail/config.hpp>
#include <boost/asio/detail/memory.hpp>
#include <boost/asio/detail/thread_context.hpp>
#include <boost/asio/detail/thread_info_base.hpp>

#include <boost/asio/detail/push_options.hpp>

namespace boost {
namespace asio {
namespace detail {

<<<<<<< HEAD
template <typename T>
=======
template <typename T, typename Purpose = thread_info_base::default_tag>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
class recycling_allocator
{
public:
  typedef T value_type;

  template <typename U>
  struct rebind
  {
<<<<<<< HEAD
    typedef recycling_allocator<U> other;
=======
    typedef recycling_allocator<U, Purpose> other;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  };

  recycling_allocator()
  {
  }

  template <typename U>
<<<<<<< HEAD
  recycling_allocator(const recycling_allocator<U>&)
=======
  recycling_allocator(const recycling_allocator<U, Purpose>&)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
  }

  T* allocate(std::size_t n)
  {
    typedef thread_context::thread_call_stack call_stack;
<<<<<<< HEAD
    void* p = thread_info_base::allocate(call_stack::top(), sizeof(T) * n);
=======
    void* p = thread_info_base::allocate(Purpose(),
        call_stack::top(), sizeof(T) * n);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    return static_cast<T*>(p);
  }

  void deallocate(T* p, std::size_t n)
  {
    typedef thread_context::thread_call_stack call_stack;
<<<<<<< HEAD
    thread_info_base::deallocate(call_stack::top(), p, sizeof(T) * n);
  }
};

template <>
class recycling_allocator<void>
=======
    thread_info_base::deallocate(Purpose(),
        call_stack::top(), p, sizeof(T) * n);
  }
};

template <typename Purpose>
class recycling_allocator<void, Purpose>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
public:
  typedef void value_type;

  template <typename U>
  struct rebind
  {
<<<<<<< HEAD
    typedef recycling_allocator<U> other;
=======
    typedef recycling_allocator<U, Purpose> other;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  };

  recycling_allocator()
  {
  }

  template <typename U>
<<<<<<< HEAD
  recycling_allocator(const recycling_allocator<U>&)
=======
  recycling_allocator(const recycling_allocator<U, Purpose>&)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
  }
};

<<<<<<< HEAD
template <typename Allocator>
=======
template <typename Allocator, typename Purpose>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
struct get_recycling_allocator
{
  typedef Allocator type;
  static type get(const Allocator& a) { return a; }
};

<<<<<<< HEAD
template <typename T>
struct get_recycling_allocator<std::allocator<T> >
{
  typedef recycling_allocator<T> type;
=======
template <typename T, typename Purpose>
struct get_recycling_allocator<std::allocator<T>, Purpose>
{
  typedef recycling_allocator<T, Purpose> type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  static type get(const std::allocator<T>&) { return type(); }
};

} // namespace detail
} // namespace asio
} // namespace boost

#include <boost/asio/detail/pop_options.hpp>

#endif // BOOST_ASIO_DETAIL_RECYCLING_ALLOCATOR_HPP

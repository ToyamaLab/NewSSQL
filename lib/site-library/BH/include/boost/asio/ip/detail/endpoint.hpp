//
// ip/detail/endpoint.hpp
// ~~~~~~~~~~~~~~~~~~~~~~
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

#ifndef BOOST_ASIO_IP_DETAIL_ENDPOINT_HPP
#define BOOST_ASIO_IP_DETAIL_ENDPOINT_HPP

#if defined(_MSC_VER) && (_MSC_VER >= 1200)
# pragma once
#endif // defined(_MSC_VER) && (_MSC_VER >= 1200)

#include <boost/asio/detail/config.hpp>
#include <string>
#include <boost/asio/detail/socket_types.hpp>
#include <boost/asio/detail/winsock_init.hpp>
#include <boost/system/error_code.hpp>
#include <boost/asio/ip/address.hpp>

#include <boost/asio/detail/push_options.hpp>

namespace boost {
namespace asio {
namespace ip {
namespace detail {

// Helper class for implementating an IP endpoint.
class endpoint
{
public:
  // Default constructor.
<<<<<<< HEAD
  BOOST_ASIO_DECL endpoint();

  // Construct an endpoint using a family and port number.
  BOOST_ASIO_DECL endpoint(int family, unsigned short port_num);

  // Construct an endpoint using an address and port number.
  BOOST_ASIO_DECL endpoint(const boost::asio::ip::address& addr,
      unsigned short port_num);

  // Copy constructor.
  endpoint(const endpoint& other)
=======
  BOOST_ASIO_DECL endpoint() BOOST_ASIO_NOEXCEPT;

  // Construct an endpoint using a family and port number.
  BOOST_ASIO_DECL endpoint(int family,
      unsigned short port_num) BOOST_ASIO_NOEXCEPT;

  // Construct an endpoint using an address and port number.
  BOOST_ASIO_DECL endpoint(const boost::asio::ip::address& addr,
      unsigned short port_num) BOOST_ASIO_NOEXCEPT;

  // Copy constructor.
  endpoint(const endpoint& other) BOOST_ASIO_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    : data_(other.data_)
  {
  }

  // Assign from another endpoint.
<<<<<<< HEAD
  endpoint& operator=(const endpoint& other)
=======
  endpoint& operator=(const endpoint& other) BOOST_ASIO_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
    data_ = other.data_;
    return *this;
  }

  // Get the underlying endpoint in the native type.
<<<<<<< HEAD
  boost::asio::detail::socket_addr_type* data()
=======
  boost::asio::detail::socket_addr_type* data() BOOST_ASIO_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
    return &data_.base;
  }

  // Get the underlying endpoint in the native type.
<<<<<<< HEAD
  const boost::asio::detail::socket_addr_type* data() const
=======
  const boost::asio::detail::socket_addr_type* data() const BOOST_ASIO_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
    return &data_.base;
  }

  // Get the underlying size of the endpoint in the native type.
<<<<<<< HEAD
  std::size_t size() const
=======
  std::size_t size() const BOOST_ASIO_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
    if (is_v4())
      return sizeof(boost::asio::detail::sockaddr_in4_type);
    else
      return sizeof(boost::asio::detail::sockaddr_in6_type);
  }

  // Set the underlying size of the endpoint in the native type.
  BOOST_ASIO_DECL void resize(std::size_t new_size);

  // Get the capacity of the endpoint in the native type.
<<<<<<< HEAD
  std::size_t capacity() const
=======
  std::size_t capacity() const BOOST_ASIO_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
    return sizeof(data_);
  }

  // Get the port associated with the endpoint.
<<<<<<< HEAD
  BOOST_ASIO_DECL unsigned short port() const;

  // Set the port associated with the endpoint.
  BOOST_ASIO_DECL void port(unsigned short port_num);

  // Get the IP address associated with the endpoint.
  BOOST_ASIO_DECL boost::asio::ip::address address() const;

  // Set the IP address associated with the endpoint.
  BOOST_ASIO_DECL void address(const boost::asio::ip::address& addr);

  // Compare two endpoints for equality.
  BOOST_ASIO_DECL friend bool operator==(
      const endpoint& e1, const endpoint& e2);

  // Compare endpoints for ordering.
  BOOST_ASIO_DECL friend bool operator<(
      const endpoint& e1, const endpoint& e2);

  // Determine whether the endpoint is IPv4.
  bool is_v4() const
=======
  BOOST_ASIO_DECL unsigned short port() const BOOST_ASIO_NOEXCEPT;

  // Set the port associated with the endpoint.
  BOOST_ASIO_DECL void port(unsigned short port_num) BOOST_ASIO_NOEXCEPT;

  // Get the IP address associated with the endpoint.
  BOOST_ASIO_DECL boost::asio::ip::address address() const BOOST_ASIO_NOEXCEPT;

  // Set the IP address associated with the endpoint.
  BOOST_ASIO_DECL void address(
      const boost::asio::ip::address& addr) BOOST_ASIO_NOEXCEPT;

  // Compare two endpoints for equality.
  BOOST_ASIO_DECL friend bool operator==(const endpoint& e1,
      const endpoint& e2) BOOST_ASIO_NOEXCEPT;

  // Compare endpoints for ordering.
  BOOST_ASIO_DECL friend bool operator<(const endpoint& e1,
      const endpoint& e2) BOOST_ASIO_NOEXCEPT;

  // Determine whether the endpoint is IPv4.
  bool is_v4() const BOOST_ASIO_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
    return data_.base.sa_family == BOOST_ASIO_OS_DEF(AF_INET);
  }

#if !defined(BOOST_ASIO_NO_IOSTREAM)
  // Convert to a string.
  BOOST_ASIO_DECL std::string to_string() const;
#endif // !defined(BOOST_ASIO_NO_IOSTREAM)

private:
  // The underlying IP socket address.
  union data_union
  {
    boost::asio::detail::socket_addr_type base;
    boost::asio::detail::sockaddr_in4_type v4;
    boost::asio::detail::sockaddr_in6_type v6;
  } data_;
};

} // namespace detail
} // namespace ip
} // namespace asio
} // namespace boost

#include <boost/asio/detail/pop_options.hpp>

#if defined(BOOST_ASIO_HEADER_ONLY)
# include <boost/asio/ip/detail/impl/endpoint.ipp>
#endif // defined(BOOST_ASIO_HEADER_ONLY)

#endif // BOOST_ASIO_IP_DETAIL_ENDPOINT_HPP

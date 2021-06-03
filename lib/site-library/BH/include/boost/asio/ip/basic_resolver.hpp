//
// ip/basic_resolver.hpp
// ~~~~~~~~~~~~~~~~~~~~~
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

#ifndef BOOST_ASIO_IP_BASIC_RESOLVER_HPP
#define BOOST_ASIO_IP_BASIC_RESOLVER_HPP

#if defined(_MSC_VER) && (_MSC_VER >= 1200)
# pragma once
#endif // defined(_MSC_VER) && (_MSC_VER >= 1200)

#include <boost/asio/detail/config.hpp>
#include <string>
#include <boost/asio/async_result.hpp>
<<<<<<< HEAD
#include <boost/asio/basic_io_object.hpp>
#include <boost/asio/detail/handler_type_requirements.hpp>
#include <boost/asio/detail/string_view.hpp>
#include <boost/asio/detail/throw_error.hpp>
#include <boost/asio/error.hpp>
#include <boost/asio/io_context.hpp>
=======
#include <boost/asio/detail/handler_type_requirements.hpp>
#include <boost/asio/detail/io_object_impl.hpp>
#include <boost/asio/detail/non_const_lvalue.hpp>
#include <boost/asio/detail/string_view.hpp>
#include <boost/asio/detail/throw_error.hpp>
#include <boost/asio/error.hpp>
#include <boost/asio/execution_context.hpp>
#include <boost/asio/executor.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/asio/ip/basic_resolver_iterator.hpp>
#include <boost/asio/ip/basic_resolver_query.hpp>
#include <boost/asio/ip/basic_resolver_results.hpp>
#include <boost/asio/ip/resolver_base.hpp>
<<<<<<< HEAD
=======
#if defined(BOOST_ASIO_WINDOWS_RUNTIME)
# include <boost/asio/detail/winrt_resolver_service.hpp>
#else
# include <boost/asio/detail/resolver_service.hpp>
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#if defined(BOOST_ASIO_HAS_MOVE)
# include <utility>
#endif // defined(BOOST_ASIO_HAS_MOVE)

<<<<<<< HEAD
#if defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
# include <boost/asio/ip/resolver_service.hpp>
#else // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
# if defined(BOOST_ASIO_WINDOWS_RUNTIME)
#  include <boost/asio/detail/winrt_resolver_service.hpp>
#  define BOOST_ASIO_SVC_T \
    boost::asio::detail::winrt_resolver_service<InternetProtocol>
# else
#  include <boost/asio/detail/resolver_service.hpp>
#  define BOOST_ASIO_SVC_T \
    boost::asio::detail::resolver_service<InternetProtocol>
# endif
#endif // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/asio/detail/push_options.hpp>

namespace boost {
namespace asio {
namespace ip {

<<<<<<< HEAD
=======
#if !defined(BOOST_ASIO_IP_BASIC_RESOLVER_FWD_DECL)
#define BOOST_ASIO_IP_BASIC_RESOLVER_FWD_DECL

// Forward declaration with defaulted arguments.
template <typename InternetProtocol, typename Executor = executor>
class basic_resolver;

#endif // !defined(BOOST_ASIO_IP_BASIC_RESOLVER_FWD_DECL)

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
/// Provides endpoint resolution functionality.
/**
 * The basic_resolver class template provides the ability to resolve a query
 * to a list of endpoints.
 *
 * @par Thread Safety
 * @e Distinct @e objects: Safe.@n
 * @e Shared @e objects: Unsafe.
 */
<<<<<<< HEAD
template <typename InternetProtocol
    BOOST_ASIO_SVC_TPARAM_DEF1(= resolver_service<InternetProtocol>)>
class basic_resolver
  : BOOST_ASIO_SVC_ACCESS basic_io_object<BOOST_ASIO_SVC_T>,
    public resolver_base
{
public:
  /// The type of the executor associated with the object.
  typedef io_context::executor_type executor_type;
=======
template <typename InternetProtocol, typename Executor>
class basic_resolver
  : public resolver_base
{
public:
  /// The type of the executor associated with the object.
  typedef Executor executor_type;

  /// Rebinds the resolver type to another executor.
  template <typename Executor1>
  struct rebind_executor
  {
    /// The resolver type when rebound to the specified executor.
    typedef basic_resolver<InternetProtocol, Executor1> other;
  };
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

  /// The protocol type.
  typedef InternetProtocol protocol_type;

  /// The endpoint type.
  typedef typename InternetProtocol::endpoint endpoint_type;

#if !defined(BOOST_ASIO_NO_DEPRECATED)
  /// (Deprecated.) The query type.
  typedef basic_resolver_query<InternetProtocol> query;

  /// (Deprecated.) The iterator type.
  typedef basic_resolver_iterator<InternetProtocol> iterator;
#endif // !defined(BOOST_ASIO_NO_DEPRECATED)

  /// The results type.
  typedef basic_resolver_results<InternetProtocol> results_type;

<<<<<<< HEAD
  /// Constructor.
  /**
   * This constructor creates a basic_resolver.
   *
   * @param io_context The io_context object that the resolver will use to
   * dispatch handlers for any asynchronous operations performed on the
   * resolver.
   */
  explicit basic_resolver(boost::asio::io_context& io_context)
    : basic_io_object<BOOST_ASIO_SVC_T>(io_context)
=======
  /// Construct with executor.
  /**
   * This constructor creates a basic_resolver.
   *
   * @param ex The I/O executor that the resolver will use, by default, to
   * dispatch handlers for any asynchronous operations performed on the
   * resolver.
   */
  explicit basic_resolver(const executor_type& ex)
    : impl_(ex)
  {
  }

  /// Construct with execution context.
  /**
   * This constructor creates a basic_resolver.
   *
   * @param context An execution context which provides the I/O executor that
   * the resolver will use, by default, to dispatch handlers for any
   * asynchronous operations performed on the resolver.
   */
  template <typename ExecutionContext>
  explicit basic_resolver(ExecutionContext& context,
      typename enable_if<
        is_convertible<ExecutionContext&, execution_context&>::value
      >::type* = 0)
    : impl_(context)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
  }

#if defined(BOOST_ASIO_HAS_MOVE) || defined(GENERATING_DOCUMENTATION)
  /// Move-construct a basic_resolver from another.
  /**
   * This constructor moves a resolver from one object to another.
   *
   * @param other The other basic_resolver object from which the move will
   * occur.
   *
   * @note Following the move, the moved-from object is in the same state as if
<<<<<<< HEAD
   * constructed using the @c basic_resolver(io_context&) constructor.
   */
  basic_resolver(basic_resolver&& other)
    : basic_io_object<BOOST_ASIO_SVC_T>(std::move(other))
=======
   * constructed using the @c basic_resolver(const executor_type&) constructor.
   */
  basic_resolver(basic_resolver&& other)
    : impl_(std::move(other.impl_))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
  }

  /// Move-assign a basic_resolver from another.
  /**
   * This assignment operator moves a resolver from one object to another.
   * Cancels any outstanding asynchronous operations associated with the target
   * object.
   *
   * @param other The other basic_resolver object from which the move will
   * occur.
   *
   * @note Following the move, the moved-from object is in the same state as if
<<<<<<< HEAD
   * constructed using the @c basic_resolver(io_context&) constructor.
   */
  basic_resolver& operator=(basic_resolver&& other)
  {
    basic_io_object<BOOST_ASIO_SVC_T>::operator=(std::move(other));
=======
   * constructed using the @c basic_resolver(const executor_type&) constructor.
   */
  basic_resolver& operator=(basic_resolver&& other)
  {
    impl_ = std::move(other.impl_);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    return *this;
  }
#endif // defined(BOOST_ASIO_HAS_MOVE) || defined(GENERATING_DOCUMENTATION)

  /// Destroys the resolver.
  /**
   * This function destroys the resolver, cancelling any outstanding
   * asynchronous wait operations associated with the resolver as if by calling
   * @c cancel.
   */
  ~basic_resolver()
  {
  }

<<<<<<< HEAD
#if defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
  // These functions are provided by basic_io_object<>.
#else // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
#if !defined(BOOST_ASIO_NO_DEPRECATED)
  /// (Deprecated: Use get_executor().) Get the io_context associated with the
  /// object.
  /**
   * This function may be used to obtain the io_context object that the I/O
   * object uses to dispatch handlers for asynchronous operations.
   *
   * @return A reference to the io_context object that the I/O object will use
   * to dispatch handlers. Ownership is not transferred to the caller.
   */
  boost::asio::io_context& get_io_context()
  {
    return basic_io_object<BOOST_ASIO_SVC_T>::get_io_context();
  }

  /// (Deprecated: Use get_executor().) Get the io_context associated with the
  /// object.
  /**
   * This function may be used to obtain the io_context object that the I/O
   * object uses to dispatch handlers for asynchronous operations.
   *
   * @return A reference to the io_context object that the I/O object will use
   * to dispatch handlers. Ownership is not transferred to the caller.
   */
  boost::asio::io_context& get_io_service()
  {
    return basic_io_object<BOOST_ASIO_SVC_T>::get_io_service();
  }
#endif // !defined(BOOST_ASIO_NO_DEPRECATED)

  /// Get the executor associated with the object.
  executor_type get_executor() BOOST_ASIO_NOEXCEPT
  {
    return basic_io_object<BOOST_ASIO_SVC_T>::get_executor();
  }
#endif // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
=======
  /// Get the executor associated with the object.
  executor_type get_executor() BOOST_ASIO_NOEXCEPT
  {
    return impl_.get_executor();
  }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

  /// Cancel any asynchronous operations that are waiting on the resolver.
  /**
   * This function forces the completion of any pending asynchronous
   * operations on the host resolver. The handler for each cancelled operation
   * will be invoked with the boost::asio::error::operation_aborted error code.
   */
  void cancel()
  {
<<<<<<< HEAD
    return this->get_service().cancel(this->get_implementation());
  }

#if !defined(BOOST_ASIO_NO_DEPRECATED)
  /// (Deprecated.) Perform forward resolution of a query to a list of entries.
=======
    return impl_.get_service().cancel(impl_.get_implementation());
  }

#if !defined(BOOST_ASIO_NO_DEPRECATED)
  /// (Deprecated: Use overload with separate host and service parameters.)
  /// Perform forward resolution of a query to a list of entries.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  /**
   * This function is used to resolve a query into a list of endpoint entries.
   *
   * @param q A query object that determines what endpoints will be returned.
   *
   * @returns A range object representing the list of endpoint entries. A
   * successful call to this function is guaranteed to return a non-empty
   * range.
   *
   * @throws boost::system::system_error Thrown on failure.
   */
  results_type resolve(const query& q)
  {
    boost::system::error_code ec;
<<<<<<< HEAD
    results_type r = this->get_service().resolve(
        this->get_implementation(), q, ec);
=======
    results_type r = impl_.get_service().resolve(
        impl_.get_implementation(), q, ec);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::asio::detail::throw_error(ec, "resolve");
    return r;
  }

<<<<<<< HEAD
  /// (Deprecated.) Perform forward resolution of a query to a list of entries.
=======
  /// (Deprecated: Use overload with separate host and service parameters.)
  /// Perform forward resolution of a query to a list of entries.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  /**
   * This function is used to resolve a query into a list of endpoint entries.
   *
   * @param q A query object that determines what endpoints will be returned.
   *
   * @param ec Set to indicate what error occurred, if any.
   *
   * @returns A range object representing the list of endpoint entries. An
   * empty range is returned if an error occurs. A successful call to this
   * function is guaranteed to return a non-empty range.
   */
  results_type resolve(const query& q, boost::system::error_code& ec)
  {
<<<<<<< HEAD
    return this->get_service().resolve(this->get_implementation(), q, ec);
=======
    return impl_.get_service().resolve(impl_.get_implementation(), q, ec);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  }
#endif // !defined(BOOST_ASIO_NO_DEPRECATED)

  /// Perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @returns A range object representing the list of endpoint entries. A
   * successful call to this function is guaranteed to return a non-empty
   * range.
   *
   * @throws boost::system::system_error Thrown on failure.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
  results_type resolve(BOOST_ASIO_STRING_VIEW_PARAM host,
      BOOST_ASIO_STRING_VIEW_PARAM service)
  {
    return resolve(host, service, resolver_base::flags());
  }

  /// Perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param ec Set to indicate what error occurred, if any.
   *
   * @returns A range object representing the list of endpoint entries. An
   * empty range is returned if an error occurs. A successful call to this
   * function is guaranteed to return a non-empty range.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
  results_type resolve(BOOST_ASIO_STRING_VIEW_PARAM host,
      BOOST_ASIO_STRING_VIEW_PARAM service, boost::system::error_code& ec)
  {
    return resolve(host, service, resolver_base::flags(), ec);
  }

  /// Perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param resolve_flags A set of flags that determine how name resolution
   * should be performed. The default flags are suitable for communication with
<<<<<<< HEAD
   * remote hosts.
=======
   * remote hosts. See the @ref resolver_base documentation for the set of
   * available flags.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * @returns A range object representing the list of endpoint entries. A
   * successful call to this function is guaranteed to return a non-empty
   * range.
   *
   * @throws boost::system::system_error Thrown on failure.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
  results_type resolve(BOOST_ASIO_STRING_VIEW_PARAM host,
      BOOST_ASIO_STRING_VIEW_PARAM service, resolver_base::flags resolve_flags)
  {
    boost::system::error_code ec;
    basic_resolver_query<protocol_type> q(static_cast<std::string>(host),
        static_cast<std::string>(service), resolve_flags);
<<<<<<< HEAD
    results_type r = this->get_service().resolve(
        this->get_implementation(), q, ec);
=======
    results_type r = impl_.get_service().resolve(
        impl_.get_implementation(), q, ec);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::asio::detail::throw_error(ec, "resolve");
    return r;
  }

  /// Perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param resolve_flags A set of flags that determine how name resolution
   * should be performed. The default flags are suitable for communication with
<<<<<<< HEAD
   * remote hosts.
=======
   * remote hosts. See the @ref resolver_base documentation for the set of
   * available flags.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * @param ec Set to indicate what error occurred, if any.
   *
   * @returns A range object representing the list of endpoint entries. An
   * empty range is returned if an error occurs. A successful call to this
   * function is guaranteed to return a non-empty range.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
  results_type resolve(BOOST_ASIO_STRING_VIEW_PARAM host,
      BOOST_ASIO_STRING_VIEW_PARAM service, resolver_base::flags resolve_flags,
      boost::system::error_code& ec)
  {
    basic_resolver_query<protocol_type> q(static_cast<std::string>(host),
        static_cast<std::string>(service), resolve_flags);
<<<<<<< HEAD
    return this->get_service().resolve(this->get_implementation(), q, ec);
=======
    return impl_.get_service().resolve(impl_.get_implementation(), q, ec);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  }

  /// Perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param protocol A protocol object, normally representing either the IPv4 or
   * IPv6 version of an internet protocol.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @returns A range object representing the list of endpoint entries. A
   * successful call to this function is guaranteed to return a non-empty
   * range.
   *
   * @throws boost::system::system_error Thrown on failure.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
  results_type resolve(const protocol_type& protocol,
      BOOST_ASIO_STRING_VIEW_PARAM host, BOOST_ASIO_STRING_VIEW_PARAM service)
  {
    return resolve(protocol, host, service, resolver_base::flags());
  }

  /// Perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param protocol A protocol object, normally representing either the IPv4 or
   * IPv6 version of an internet protocol.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param ec Set to indicate what error occurred, if any.
   *
   * @returns A range object representing the list of endpoint entries. An
   * empty range is returned if an error occurs. A successful call to this
   * function is guaranteed to return a non-empty range.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
  results_type resolve(const protocol_type& protocol,
      BOOST_ASIO_STRING_VIEW_PARAM host, BOOST_ASIO_STRING_VIEW_PARAM service,
      boost::system::error_code& ec)
  {
    return resolve(protocol, host, service, resolver_base::flags(), ec);
  }

  /// Perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param protocol A protocol object, normally representing either the IPv4 or
   * IPv6 version of an internet protocol.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param resolve_flags A set of flags that determine how name resolution
   * should be performed. The default flags are suitable for communication with
<<<<<<< HEAD
   * remote hosts.
=======
   * remote hosts. See the @ref resolver_base documentation for the set of
   * available flags.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * @returns A range object representing the list of endpoint entries. A
   * successful call to this function is guaranteed to return a non-empty
   * range.
   *
   * @throws boost::system::system_error Thrown on failure.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
  results_type resolve(const protocol_type& protocol,
      BOOST_ASIO_STRING_VIEW_PARAM host, BOOST_ASIO_STRING_VIEW_PARAM service,
      resolver_base::flags resolve_flags)
  {
    boost::system::error_code ec;
    basic_resolver_query<protocol_type> q(
        protocol, static_cast<std::string>(host),
        static_cast<std::string>(service), resolve_flags);
<<<<<<< HEAD
    results_type r = this->get_service().resolve(
        this->get_implementation(), q, ec);
=======
    results_type r = impl_.get_service().resolve(
        impl_.get_implementation(), q, ec);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::asio::detail::throw_error(ec, "resolve");
    return r;
  }

  /// Perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param protocol A protocol object, normally representing either the IPv4 or
   * IPv6 version of an internet protocol.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param resolve_flags A set of flags that determine how name resolution
   * should be performed. The default flags are suitable for communication with
<<<<<<< HEAD
   * remote hosts.
=======
   * remote hosts. See the @ref resolver_base documentation for the set of
   * available flags.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * @param ec Set to indicate what error occurred, if any.
   *
   * @returns A range object representing the list of endpoint entries. An
   * empty range is returned if an error occurs. A successful call to this
   * function is guaranteed to return a non-empty range.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
  results_type resolve(const protocol_type& protocol,
      BOOST_ASIO_STRING_VIEW_PARAM host, BOOST_ASIO_STRING_VIEW_PARAM service,
      resolver_base::flags resolve_flags, boost::system::error_code& ec)
  {
    basic_resolver_query<protocol_type> q(
        protocol, static_cast<std::string>(host),
        static_cast<std::string>(service), resolve_flags);
<<<<<<< HEAD
    return this->get_service().resolve(this->get_implementation(), q, ec);
  }

#if !defined(BOOST_ASIO_NO_DEPRECATED)
  /// (Deprecated.) Asynchronously perform forward resolution of a query to a
  /// list of entries.
=======
    return impl_.get_service().resolve(impl_.get_implementation(), q, ec);
  }

#if !defined(BOOST_ASIO_NO_DEPRECATED)
  /// (Deprecated: Use overload with separate host and service parameters.)
  /// Asynchronously perform forward resolution of a query to a list of entries.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  /**
   * This function is used to asynchronously resolve a query into a list of
   * endpoint entries.
   *
   * @param q A query object that determines what endpoints will be returned.
   *
   * @param handler The handler to be called when the resolve operation
   * completes. Copies will be made of the handler as required. The function
   * signature of the handler must be:
   * @code void handler(
   *   const boost::system::error_code& error, // Result of operation.
   *   resolver::results_type results // Resolved endpoints as a range.
   * ); @endcode
   * Regardless of whether the asynchronous operation completes immediately or
<<<<<<< HEAD
   * not, the handler will not be invoked from within this function. Invocation
   * of the handler will be performed in a manner equivalent to using
   * boost::asio::io_context::post().
=======
   * not, the handler will not be invoked from within this function. On
   * immediate completion, invocation of the handler will be performed in a
   * manner equivalent to using boost::asio::post().
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * A successful resolve operation is guaranteed to pass a non-empty range to
   * the handler.
   */
<<<<<<< HEAD
  template <typename ResolveHandler>
  BOOST_ASIO_INITFN_RESULT_TYPE(ResolveHandler,
      void (boost::system::error_code, results_type))
  async_resolve(const query& q,
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler)
  {
    // If you get an error on the following line it means that your handler does
    // not meet the documented type requirements for a ResolveHandler.
    BOOST_ASIO_RESOLVE_HANDLER_CHECK(
        ResolveHandler, handler, results_type) type_check;

#if defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
    return this->get_service().async_resolve(this->get_implementation(), q,
        BOOST_ASIO_MOVE_CAST(ResolveHandler)(handler));
#else // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
    boost::asio::async_completion<ResolveHandler,
      void (boost::system::error_code, results_type)> init(handler);

    this->get_service().async_resolve(
        this->get_implementation(), q, init.completion_handler);

    return init.result.get();
#endif // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
=======
  template <
      BOOST_ASIO_COMPLETION_TOKEN_FOR(void (boost::system::error_code,
        results_type)) ResolveHandler
          BOOST_ASIO_DEFAULT_COMPLETION_TOKEN_TYPE(executor_type)>
  BOOST_ASIO_INITFN_AUTO_RESULT_TYPE(ResolveHandler,
      void (boost::system::error_code, results_type))
  async_resolve(const query& q,
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler
        BOOST_ASIO_DEFAULT_COMPLETION_TOKEN(executor_type))
  {
    return boost::asio::async_initiate<ResolveHandler,
      void (boost::system::error_code, results_type)>(
        initiate_async_resolve(this), handler, q);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  }
#endif // !defined(BOOST_ASIO_NO_DEPRECATED)

  /// Asynchronously perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param handler The handler to be called when the resolve operation
   * completes. Copies will be made of the handler as required. The function
   * signature of the handler must be:
   * @code void handler(
   *   const boost::system::error_code& error, // Result of operation.
   *   resolver::results_type results // Resolved endpoints as a range.
   * ); @endcode
   * Regardless of whether the asynchronous operation completes immediately or
<<<<<<< HEAD
   * not, the handler will not be invoked from within this function. Invocation
   * of the handler will be performed in a manner equivalent to using
   * boost::asio::io_context::post().
=======
   * not, the handler will not be invoked from within this function. On
   * immediate completion, invocation of the handler will be performed in a
   * manner equivalent to using boost::asio::post().
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * A successful resolve operation is guaranteed to pass a non-empty range to
   * the handler.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
<<<<<<< HEAD
  template <typename ResolveHandler>
  BOOST_ASIO_INITFN_RESULT_TYPE(ResolveHandler,
      void (boost::system::error_code, results_type))
  async_resolve(BOOST_ASIO_STRING_VIEW_PARAM host,
      BOOST_ASIO_STRING_VIEW_PARAM service,
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler)
=======
  template <
      BOOST_ASIO_COMPLETION_TOKEN_FOR(void (boost::system::error_code,
        results_type)) ResolveHandler
          BOOST_ASIO_DEFAULT_COMPLETION_TOKEN_TYPE(executor_type)>
  BOOST_ASIO_INITFN_AUTO_RESULT_TYPE(ResolveHandler,
      void (boost::system::error_code, results_type))
  async_resolve(BOOST_ASIO_STRING_VIEW_PARAM host,
      BOOST_ASIO_STRING_VIEW_PARAM service,
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler
        BOOST_ASIO_DEFAULT_COMPLETION_TOKEN(executor_type))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
    return async_resolve(host, service, resolver_base::flags(),
        BOOST_ASIO_MOVE_CAST(ResolveHandler)(handler));
  }

  /// Asynchronously perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param resolve_flags A set of flags that determine how name resolution
   * should be performed. The default flags are suitable for communication with
<<<<<<< HEAD
   * remote hosts.
=======
   * remote hosts. See the @ref resolver_base documentation for the set of
   * available flags.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * @param handler The handler to be called when the resolve operation
   * completes. Copies will be made of the handler as required. The function
   * signature of the handler must be:
   * @code void handler(
   *   const boost::system::error_code& error, // Result of operation.
   *   resolver::results_type results // Resolved endpoints as a range.
   * ); @endcode
   * Regardless of whether the asynchronous operation completes immediately or
<<<<<<< HEAD
   * not, the handler will not be invoked from within this function. Invocation
   * of the handler will be performed in a manner equivalent to using
   * boost::asio::io_context::post().
=======
   * not, the handler will not be invoked from within this function. On
   * immediate completion, invocation of the handler will be performed in a
   * manner equivalent to using boost::asio::post().
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * A successful resolve operation is guaranteed to pass a non-empty range to
   * the handler.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
<<<<<<< HEAD
  template <typename ResolveHandler>
  BOOST_ASIO_INITFN_RESULT_TYPE(ResolveHandler,
=======
  template <
      BOOST_ASIO_COMPLETION_TOKEN_FOR(void (boost::system::error_code,
        results_type)) ResolveHandler
          BOOST_ASIO_DEFAULT_COMPLETION_TOKEN_TYPE(executor_type)>
  BOOST_ASIO_INITFN_AUTO_RESULT_TYPE(ResolveHandler,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      void (boost::system::error_code, results_type))
  async_resolve(BOOST_ASIO_STRING_VIEW_PARAM host,
      BOOST_ASIO_STRING_VIEW_PARAM service,
      resolver_base::flags resolve_flags,
<<<<<<< HEAD
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler)
  {
    // If you get an error on the following line it means that your handler does
    // not meet the documented type requirements for a ResolveHandler.
    BOOST_ASIO_RESOLVE_HANDLER_CHECK(
        ResolveHandler, handler, results_type) type_check;

    basic_resolver_query<protocol_type> q(static_cast<std::string>(host),
        static_cast<std::string>(service), resolve_flags);

#if defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
    return this->get_service().async_resolve(this->get_implementation(), q,
        BOOST_ASIO_MOVE_CAST(ResolveHandler)(handler));
#else // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
    boost::asio::async_completion<ResolveHandler,
      void (boost::system::error_code, results_type)> init(handler);

    this->get_service().async_resolve(
        this->get_implementation(), q, init.completion_handler);

    return init.result.get();
#endif // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
=======
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler
        BOOST_ASIO_DEFAULT_COMPLETION_TOKEN(executor_type))
  {
    basic_resolver_query<protocol_type> q(static_cast<std::string>(host),
        static_cast<std::string>(service), resolve_flags);

    return boost::asio::async_initiate<ResolveHandler,
      void (boost::system::error_code, results_type)>(
        initiate_async_resolve(this), handler, q);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  }

  /// Asynchronously perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param protocol A protocol object, normally representing either the IPv4 or
   * IPv6 version of an internet protocol.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param handler The handler to be called when the resolve operation
   * completes. Copies will be made of the handler as required. The function
   * signature of the handler must be:
   * @code void handler(
   *   const boost::system::error_code& error, // Result of operation.
   *   resolver::results_type results // Resolved endpoints as a range.
   * ); @endcode
   * Regardless of whether the asynchronous operation completes immediately or
<<<<<<< HEAD
   * not, the handler will not be invoked from within this function. Invocation
   * of the handler will be performed in a manner equivalent to using
   * boost::asio::io_context::post().
=======
   * not, the handler will not be invoked from within this function. On
   * immediate completion, invocation of the handler will be performed in a
   * manner equivalent to using boost::asio::post().
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * A successful resolve operation is guaranteed to pass a non-empty range to
   * the handler.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
<<<<<<< HEAD
  template <typename ResolveHandler>
  BOOST_ASIO_INITFN_RESULT_TYPE(ResolveHandler,
      void (boost::system::error_code, results_type))
  async_resolve(const protocol_type& protocol,
      BOOST_ASIO_STRING_VIEW_PARAM host, BOOST_ASIO_STRING_VIEW_PARAM service,
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler)
=======
  template <
      BOOST_ASIO_COMPLETION_TOKEN_FOR(void (boost::system::error_code,
        results_type)) ResolveHandler
          BOOST_ASIO_DEFAULT_COMPLETION_TOKEN_TYPE(executor_type)>
  BOOST_ASIO_INITFN_AUTO_RESULT_TYPE(ResolveHandler,
      void (boost::system::error_code, results_type))
  async_resolve(const protocol_type& protocol,
      BOOST_ASIO_STRING_VIEW_PARAM host, BOOST_ASIO_STRING_VIEW_PARAM service,
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler
        BOOST_ASIO_DEFAULT_COMPLETION_TOKEN(executor_type))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  {
    return async_resolve(protocol, host, service, resolver_base::flags(),
        BOOST_ASIO_MOVE_CAST(ResolveHandler)(handler));
  }

  /// Asynchronously perform forward resolution of a query to a list of entries.
  /**
   * This function is used to resolve host and service names into a list of
   * endpoint entries.
   *
   * @param protocol A protocol object, normally representing either the IPv4 or
   * IPv6 version of an internet protocol.
   *
   * @param host A string identifying a location. May be a descriptive name or
   * a numeric address string. If an empty string and the passive flag has been
   * specified, the resolved endpoints are suitable for local service binding.
   * If an empty string and passive is not specified, the resolved endpoints
   * will use the loopback address.
   *
   * @param service A string identifying the requested service. This may be a
   * descriptive name or a numeric string corresponding to a port number. May
   * be an empty string, in which case all resolved endpoints will have a port
   * number of 0.
   *
   * @param resolve_flags A set of flags that determine how name resolution
   * should be performed. The default flags are suitable for communication with
<<<<<<< HEAD
   * remote hosts.
=======
   * remote hosts. See the @ref resolver_base documentation for the set of
   * available flags.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * @param handler The handler to be called when the resolve operation
   * completes. Copies will be made of the handler as required. The function
   * signature of the handler must be:
   * @code void handler(
   *   const boost::system::error_code& error, // Result of operation.
   *   resolver::results_type results // Resolved endpoints as a range.
   * ); @endcode
   * Regardless of whether the asynchronous operation completes immediately or
<<<<<<< HEAD
   * not, the handler will not be invoked from within this function. Invocation
   * of the handler will be performed in a manner equivalent to using
   * boost::asio::io_context::post().
=======
   * not, the handler will not be invoked from within this function. On
   * immediate completion, invocation of the handler will be performed in a
   * manner equivalent to using boost::asio::post().
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * A successful resolve operation is guaranteed to pass a non-empty range to
   * the handler.
   *
   * @note On POSIX systems, host names may be locally defined in the file
   * <tt>/etc/hosts</tt>. On Windows, host names may be defined in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\hosts</tt>. Remote host name
   * resolution is performed using DNS. Operating systems may use additional
   * locations when resolving host names (such as NETBIOS names on Windows).
   *
   * On POSIX systems, service names are typically defined in the file
   * <tt>/etc/services</tt>. On Windows, service names may be found in the file
   * <tt>c:\\windows\\system32\\drivers\\etc\\services</tt>. Operating systems
   * may use additional locations when resolving service names.
   */
<<<<<<< HEAD
  template <typename ResolveHandler>
  BOOST_ASIO_INITFN_RESULT_TYPE(ResolveHandler,
=======
  template <
      BOOST_ASIO_COMPLETION_TOKEN_FOR(void (boost::system::error_code,
        results_type)) ResolveHandler
          BOOST_ASIO_DEFAULT_COMPLETION_TOKEN_TYPE(executor_type)>
  BOOST_ASIO_INITFN_AUTO_RESULT_TYPE(ResolveHandler,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      void (boost::system::error_code, results_type))
  async_resolve(const protocol_type& protocol,
      BOOST_ASIO_STRING_VIEW_PARAM host, BOOST_ASIO_STRING_VIEW_PARAM service,
      resolver_base::flags resolve_flags,
<<<<<<< HEAD
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler)
  {
    // If you get an error on the following line it means that your handler does
    // not meet the documented type requirements for a ResolveHandler.
    BOOST_ASIO_RESOLVE_HANDLER_CHECK(
        ResolveHandler, handler, results_type) type_check;

=======
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler
        BOOST_ASIO_DEFAULT_COMPLETION_TOKEN(executor_type))
  {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    basic_resolver_query<protocol_type> q(
        protocol, static_cast<std::string>(host),
        static_cast<std::string>(service), resolve_flags);

<<<<<<< HEAD
#if defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
    return this->get_service().async_resolve(this->get_implementation(), q,
        BOOST_ASIO_MOVE_CAST(ResolveHandler)(handler));
#else // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
    boost::asio::async_completion<ResolveHandler,
      void (boost::system::error_code, results_type)> init(handler);

    this->get_service().async_resolve(
        this->get_implementation(), q, init.completion_handler);

    return init.result.get();
#endif // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
=======
    return boost::asio::async_initiate<ResolveHandler,
      void (boost::system::error_code, results_type)>(
        initiate_async_resolve(this), handler, q);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  }

  /// Perform reverse resolution of an endpoint to a list of entries.
  /**
   * This function is used to resolve an endpoint into a list of endpoint
   * entries.
   *
   * @param e An endpoint object that determines what endpoints will be
   * returned.
   *
   * @returns A range object representing the list of endpoint entries. A
   * successful call to this function is guaranteed to return a non-empty
   * range.
   *
   * @throws boost::system::system_error Thrown on failure.
   */
  results_type resolve(const endpoint_type& e)
  {
    boost::system::error_code ec;
<<<<<<< HEAD
    results_type i = this->get_service().resolve(
        this->get_implementation(), e, ec);
=======
    results_type i = impl_.get_service().resolve(
        impl_.get_implementation(), e, ec);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::asio::detail::throw_error(ec, "resolve");
    return i;
  }

  /// Perform reverse resolution of an endpoint to a list of entries.
  /**
   * This function is used to resolve an endpoint into a list of endpoint
   * entries.
   *
   * @param e An endpoint object that determines what endpoints will be
   * returned.
   *
   * @param ec Set to indicate what error occurred, if any.
   *
   * @returns A range object representing the list of endpoint entries. An
   * empty range is returned if an error occurs. A successful call to this
   * function is guaranteed to return a non-empty range.
   */
  results_type resolve(const endpoint_type& e, boost::system::error_code& ec)
  {
<<<<<<< HEAD
    return this->get_service().resolve(this->get_implementation(), e, ec);
=======
    return impl_.get_service().resolve(impl_.get_implementation(), e, ec);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  }

  /// Asynchronously perform reverse resolution of an endpoint to a list of
  /// entries.
  /**
   * This function is used to asynchronously resolve an endpoint into a list of
   * endpoint entries.
   *
   * @param e An endpoint object that determines what endpoints will be
   * returned.
   *
   * @param handler The handler to be called when the resolve operation
   * completes. Copies will be made of the handler as required. The function
   * signature of the handler must be:
   * @code void handler(
   *   const boost::system::error_code& error, // Result of operation.
   *   resolver::results_type results // Resolved endpoints as a range.
   * ); @endcode
   * Regardless of whether the asynchronous operation completes immediately or
<<<<<<< HEAD
   * not, the handler will not be invoked from within this function. Invocation
   * of the handler will be performed in a manner equivalent to using
   * boost::asio::io_context::post().
=======
   * not, the handler will not be invoked from within this function. On
   * immediate completion, invocation of the handler will be performed in a
   * manner equivalent to using boost::asio::post().
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   *
   * A successful resolve operation is guaranteed to pass a non-empty range to
   * the handler.
   */
<<<<<<< HEAD
  template <typename ResolveHandler>
  BOOST_ASIO_INITFN_RESULT_TYPE(ResolveHandler,
      void (boost::system::error_code, results_type))
  async_resolve(const endpoint_type& e,
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler)
  {
    // If you get an error on the following line it means that your handler does
    // not meet the documented type requirements for a ResolveHandler.
    BOOST_ASIO_RESOLVE_HANDLER_CHECK(
        ResolveHandler, handler, results_type) type_check;

#if defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
    return this->get_service().async_resolve(this->get_implementation(), e,
        BOOST_ASIO_MOVE_CAST(ResolveHandler)(handler));
#else // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
    boost::asio::async_completion<ResolveHandler,
      void (boost::system::error_code, results_type)> init(handler);

    this->get_service().async_resolve(
        this->get_implementation(), e, init.completion_handler);

    return init.result.get();
#endif // defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
  }
=======
  template <
      BOOST_ASIO_COMPLETION_TOKEN_FOR(void (boost::system::error_code,
        results_type)) ResolveHandler
          BOOST_ASIO_DEFAULT_COMPLETION_TOKEN_TYPE(executor_type)>
  BOOST_ASIO_INITFN_AUTO_RESULT_TYPE(ResolveHandler,
      void (boost::system::error_code, results_type))
  async_resolve(const endpoint_type& e,
      BOOST_ASIO_MOVE_ARG(ResolveHandler) handler
        BOOST_ASIO_DEFAULT_COMPLETION_TOKEN(executor_type))
  {
    return boost::asio::async_initiate<ResolveHandler,
      void (boost::system::error_code, results_type)>(
        initiate_async_resolve(this), handler, e);
  }

private:
  // Disallow copying and assignment.
  basic_resolver(const basic_resolver&) BOOST_ASIO_DELETED;
  basic_resolver& operator=(const basic_resolver&) BOOST_ASIO_DELETED;

  class initiate_async_resolve
  {
  public:
    typedef Executor executor_type;

    explicit initiate_async_resolve(basic_resolver* self)
      : self_(self)
    {
    }

    executor_type get_executor() const BOOST_ASIO_NOEXCEPT
    {
      return self_->get_executor();
    }

    template <typename ResolveHandler, typename Query>
    void operator()(BOOST_ASIO_MOVE_ARG(ResolveHandler) handler,
        const Query& q) const
    {
      // If you get an error on the following line it means that your handler
      // does not meet the documented type requirements for a ResolveHandler.
      BOOST_ASIO_RESOLVE_HANDLER_CHECK(
          ResolveHandler, handler, results_type) type_check;

      boost::asio::detail::non_const_lvalue<ResolveHandler> handler2(handler);
      self_->impl_.get_service().async_resolve(
          self_->impl_.get_implementation(), q, handler2.value,
          self_->impl_.get_implementation_executor());
    }

  private:
    basic_resolver* self_;
  };

# if defined(BOOST_ASIO_WINDOWS_RUNTIME)
  boost::asio::detail::io_object_impl<
    boost::asio::detail::winrt_resolver_service<InternetProtocol>,
    Executor> impl_;
# else
  boost::asio::detail::io_object_impl<
    boost::asio::detail::resolver_service<InternetProtocol>,
    Executor> impl_;
# endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

} // namespace ip
} // namespace asio
} // namespace boost

#include <boost/asio/detail/pop_options.hpp>

<<<<<<< HEAD
#if !defined(BOOST_ASIO_ENABLE_OLD_SERVICES)
# undef BOOST_ASIO_SVC_T
#endif // !defined(BOOST_ASIO_ENABLE_OLD_SERVICES)

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif // BOOST_ASIO_IP_BASIC_RESOLVER_HPP

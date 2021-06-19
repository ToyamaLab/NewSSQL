#ifndef POSIX_TIME_DURATION_HPP___
#define POSIX_TIME_DURATION_HPP___

/* Copyright (c) 2002,2003 CrystalClear Software, Inc.
 * Use, modification and distribution is subject to the 
 * Boost Software License, Version 1.0. (See accompanying
 * file LICENSE_1_0.txt or http://www.boost.org/LICENSE_1_0.txt)
 * Author: Jeff Garland
 * $Date$
 */

<<<<<<< HEAD
#include <boost/date_time/compiler_config.hpp>
#include <boost/date_time/posix_time/posix_time_config.hpp>
=======
#include <boost/core/enable_if.hpp>
#include <boost/date_time/compiler_config.hpp>
#include <boost/date_time/posix_time/posix_time_config.hpp>
#include <boost/numeric/conversion/cast.hpp>
#include <boost/type_traits/is_integral.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost {
namespace posix_time {

  //! Allows expression of durations as an hour count
<<<<<<< HEAD
=======
  //! The argument must be an integral type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  /*! \ingroup time_basics
   */
  class BOOST_SYMBOL_VISIBLE hours : public time_duration
  {
  public:
<<<<<<< HEAD
    explicit hours(long h) :
      time_duration(static_cast<hour_type>(h),0,0)
=======
      template <typename T>
      explicit hours(T const& h,
          typename boost::enable_if<boost::is_integral<T>, void>::type* = BOOST_DATE_TIME_NULLPTR) :
      time_duration(numeric_cast<hour_type>(h), 0, 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {}
  };

  //! Allows expression of durations as a minute count
<<<<<<< HEAD
=======
  //! The argument must be an integral type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  /*! \ingroup time_basics
   */
  class BOOST_SYMBOL_VISIBLE minutes : public time_duration
  {
  public:
<<<<<<< HEAD
    explicit minutes(long m) :
      time_duration(0,static_cast<min_type>(m),0)
=======
      template <typename T>
      explicit minutes(T const& m,
          typename boost::enable_if<boost::is_integral<T>, void>::type* = BOOST_DATE_TIME_NULLPTR) :
      time_duration(0, numeric_cast<min_type>(m),0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {}
  };

  //! Allows expression of durations as a seconds count
<<<<<<< HEAD
=======
  //! The argument must be an integral type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  /*! \ingroup time_basics
   */
  class BOOST_SYMBOL_VISIBLE seconds : public time_duration
  {
  public:
<<<<<<< HEAD
    explicit seconds(long s) :
      time_duration(0,0,static_cast<sec_type>(s))
=======
      template <typename T>
      explicit seconds(T const& s,
          typename boost::enable_if<boost::is_integral<T>, void>::type* = BOOST_DATE_TIME_NULLPTR) :
      time_duration(0,0, numeric_cast<sec_type>(s))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {}
  };


  //! Allows expression of durations as milli seconds
  /*! \ingroup time_basics
   */
  typedef date_time::subsecond_duration<time_duration,1000> millisec;
  typedef date_time::subsecond_duration<time_duration,1000> milliseconds;

  //! Allows expression of durations as micro seconds
  /*! \ingroup time_basics
   */
  typedef date_time::subsecond_duration<time_duration,1000000> microsec;
  typedef date_time::subsecond_duration<time_duration,1000000> microseconds;

  //This is probably not needed anymore...
#if defined(BOOST_DATE_TIME_HAS_NANOSECONDS)

  //! Allows expression of durations as nano seconds
  /*! \ingroup time_basics
   */
  typedef date_time::subsecond_duration<time_duration,1000000000> nanosec;
  typedef date_time::subsecond_duration<time_duration,1000000000> nanoseconds;

<<<<<<< HEAD

#endif




=======
#endif

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
} }//namespace posix_time


#endif


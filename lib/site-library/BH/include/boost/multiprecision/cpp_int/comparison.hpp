///////////////////////////////////////////////////////////////
//  Copyright 2012 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
<<<<<<< HEAD
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_
=======
//  LICENSE_1_0.txt or copy at https://www.boost.org/LICENSE_1_0.txt
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
//
// Comparison operators for cpp_int_backend:
//
#ifndef BOOST_MP_CPP_INT_COMPARISON_HPP
#define BOOST_MP_CPP_INT_COMPARISON_HPP

#include <boost/type_traits/make_unsigned.hpp>
<<<<<<< HEAD

namespace boost{ namespace multiprecision{ namespace backends{

#ifdef BOOST_MSVC
#pragma warning(push)
#pragma warning(disable:4018 4389 4996)
=======
#include <boost/multiprecision/detail/constexpr.hpp>

namespace boost { namespace multiprecision { namespace backends {

#ifdef BOOST_MSVC
#pragma warning(push)
#pragma warning(disable : 4018 4389 4996)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif

//
// Start with non-trivial cpp_int's:
//
template <unsigned MinBits, unsigned MaxBits, cpp_integer_type SignType, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator> >::value, 
      bool
   >::type 
   eval_eq(const cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator>& a, const cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator>& b) BOOST_NOEXCEPT
{
#if BOOST_WORKAROUND(BOOST_MSVC, >= 1600)
   return (a.sign() == b.sign())
      && (a.size() == b.size())
      && std::equal(a.limbs(), a.limbs() + a.size(), 
         stdext::checked_array_iterator<cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator>::const_limb_pointer>(b.limbs(), b.size()));
#else
   return (a.sign() == b.sign())
      && (a.size() == b.size())
      && std::equal(a.limbs(), a.limbs() + a.size(), b.limbs());
#endif
}
template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2>
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value 
      && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value,
      bool
   >::type 
   eval_eq(const cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& a, const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& b) BOOST_NOEXCEPT
{
#if BOOST_WORKAROUND(BOOST_MSVC, >= 1600)
   return (a.sign() == b.sign())
      && (a.size() == b.size())
      && std::equal(a.limbs(), a.limbs() + a.size(), stdext::checked_array_iterator<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>::const_limb_pointer>(b.limbs(), b.size()));
#else
   return (a.sign() == b.sign())
      && (a.size() == b.size())
      && std::equal(a.limbs(), a.limbs() + a.size(), b.limbs());
#endif
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   return (a.sign() == false)
      && (a.size() == 1)
      && (*a.limbs() == b);
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
{
   return (a.sign() == (b < 0))
      && (a.size() == 1)
      && (*a.limbs() == boost::multiprecision::detail::unsigned_abs(b));
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   return (a.size() == 1)
      && (*a.limbs() == b);
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator>& a, const cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator>& b) BOOST_NOEXCEPT
{
   return (a.sign() == b.sign()) && (a.size() == b.size()) && std_constexpr::equal(a.limbs(), a.limbs() + a.size(), b.limbs());
}
template <unsigned MinBits1, unsigned MaxBits1, cpp_integer_type SignType1, cpp_int_check_type Checked1, class Allocator1, unsigned MinBits2, unsigned MaxBits2, cpp_integer_type SignType2, cpp_int_check_type Checked2, class Allocator2>
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1> >::value && !is_trivial_cpp_int<cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits1, MaxBits1, SignType1, Checked1, Allocator1>& a, const cpp_int_backend<MinBits2, MaxBits2, SignType2, Checked2, Allocator2>& b) BOOST_NOEXCEPT
{
   return (a.sign() == b.sign()) && (a.size() == b.size()) && std_constexpr::equal(a.limbs(), a.limbs() + a.size(), b.limbs());
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   return (a.sign() == false) && (a.size() == 1) && (*a.limbs() == b);
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
{
   return (a.sign() == (b < 0)) && (a.size() == 1) && (*a.limbs() == boost::multiprecision::detail::unsigned_abs(b));
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   return (a.size() == 1) && (*a.limbs() == b);
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return (b < 0) ? eval_eq(a, cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>(b)) : eval_eq(a, static_cast<limb_type>(b)); // Use bit pattern of b for comparison
}

template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   if(a.sign())
      return true;
   if(a.size() > 1)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   if (a.sign())
      return true;
   if (a.size() > 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return false;
   return *a.limbs() < b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
inline typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
{
   if((b == 0) || (a.sign() != (b < 0)))
      return a.sign();
   if(a.sign())
   {
      if(a.size() > 1)
=======
inline BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
{
   if ((b == 0) || (a.sign() != (b < 0)))
      return a.sign();
   if (a.sign())
   {
      if (a.size() > 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         return true;
      return *a.limbs() > boost::multiprecision::detail::unsigned_abs(b);
   }
   else
   {
<<<<<<< HEAD
      if(a.size() > 1)
=======
      if (a.size() > 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         return false;
      return *a.limbs() < boost::multiprecision::detail::unsigned_abs(b);
   }
}

template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   if(a.size() > 1)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   if (a.size() > 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return false;
   return *a.limbs() < b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return (b < 0) ? a.compare(b) < 0 : eval_lt(a, static_cast<limb_type>(b)); // Use bit pattern of b for comparison
}

template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   if(a.sign())
      return false;
   if(a.size() > 1)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   if (a.sign())
      return false;
   if (a.size() > 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return true;
   return *a.limbs() > b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
inline typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
{
   if(b == 0)
      return !a.sign() && ((a.size() > 1) || *a.limbs());
   if(a.sign() != (b < 0))
      return !a.sign();
   if(a.sign())
   {
      if(a.size() > 1)
=======
inline BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
{
   if (b == 0)
      return !a.sign() && ((a.size() > 1) || *a.limbs());
   if (a.sign() != (b < 0))
      return !a.sign();
   if (a.sign())
   {
      if (a.size() > 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         return false;
      return *a.limbs() < boost::multiprecision::detail::unsigned_abs(b);
   }
   else
   {
<<<<<<< HEAD
      if(a.size() > 1)
=======
      if (a.size() > 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         return true;
      return *a.limbs() > boost::multiprecision::detail::unsigned_abs(b);
   }
}

template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   if(a.size() > 1)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, limb_type b) BOOST_NOEXCEPT
{
   if (a.size() > 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return true;
   return *a.limbs() > b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class Allocator>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    !is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, Allocator>& a, signed_limb_type b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return (b < 0) ? a.compare(b) > 0 : eval_gt(a, static_cast<limb_type>(b)); // Use bit pattern of b for comparison.
}
//
// And again for trivial cpp_ints:
//
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value, 
      bool
   >::value eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::value
eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return (a.sign() == b.sign()) && (*a.limbs() == *b.limbs());
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value, 
      bool
   >::value eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::value
eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return *a.limbs() == *b.limbs();
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class U>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value, 
      bool
   >::type eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return !a.sign() && (*a.limbs() == b);
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class S>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value, 
      bool
   >::type eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return (a.sign() == (b < 0)) && (*a.limbs() == boost::multiprecision::detail::unsigned_abs(b));
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class U>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value, 
      bool
   >::type eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return *a.limbs() == b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class S>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value, 
      bool
   >::type eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   typedef typename make_unsigned<S>::type ui_type;
   if(b < 0)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::type
eval_eq(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   typedef typename make_unsigned<S>::type ui_type;
   if (b < 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> t(b);
      return *a.limbs() == *t.limbs();
   }
   else
   {
      return *a.limbs() == static_cast<ui_type>(b);
   }
}

template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& b) BOOST_NOEXCEPT
{
   if(a.sign() != b.sign())
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& b) BOOST_NOEXCEPT
{
   if (a.sign() != b.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return a.sign();
   return a.sign() ? *a.limbs() > *b.limbs() : *a.limbs() < *b.limbs();
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return *a.limbs() < *b.limbs();
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class U>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value, 
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
{
   if(a.sign())
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
{
   if (a.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return true;
   return *a.limbs() < b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class S>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value, 
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   if(a.sign() != (b < 0))
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   if (a.sign() != (b < 0))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return a.sign();
   return a.sign() ? (*a.limbs() > boost::multiprecision::detail::unsigned_abs(b)) : (*a.limbs() < boost::multiprecision::detail::unsigned_abs(b));
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class U>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value, 
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return *a.limbs() < b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class S>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value, 
      bool
   >::type eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   typedef typename make_unsigned<S>::type ui_type;
   if(b < 0)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::type
eval_lt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   typedef typename make_unsigned<S>::type ui_type;
   if (b < 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> t(b);
      return *a.limbs() < *t.limbs();
   }
   else
   {
      return *a.limbs() < static_cast<ui_type>(b);
   }
}

template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& b) BOOST_NOEXCEPT
{
   if(a.sign() != b.sign())
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& b) BOOST_NOEXCEPT
{
   if (a.sign() != b.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return !a.sign();
   return a.sign() ? *a.limbs() < *b.limbs() : *a.limbs() > *b.limbs();
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return *a.limbs() > *b.limbs();
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class U>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value, 
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
{
   if(a.sign())
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
{
   if (a.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return false;
   return *a.limbs() > b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class S>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value, 
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   if(a.sign() != (b < 0))
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, signed_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   if (a.sign() != (b < 0))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      return !a.sign();
   return a.sign() ? (*a.limbs() < boost::multiprecision::detail::unsigned_abs(b)) : (*a.limbs() > boost::multiprecision::detail::unsigned_abs(b));
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class U>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value, 
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_unsigned<U>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, U b) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return *a.limbs() > b;
}
template <unsigned MinBits, unsigned MaxBits, cpp_int_check_type Checked, class S>
<<<<<<< HEAD
BOOST_MP_FORCEINLINE typename enable_if_c<
      is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value, 
      bool
   >::type eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   typedef typename make_unsigned<S>::type ui_type;
   if(b < 0)
=======
BOOST_MP_FORCEINLINE BOOST_MP_CXX14_CONSTEXPR typename enable_if_c<
    is_signed<S>::value && is_trivial_cpp_int<cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> >::value,
    bool>::type
eval_gt(const cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void>& a, S b) BOOST_NOEXCEPT
{
   typedef typename make_unsigned<S>::type ui_type;
   if (b < 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   {
      cpp_int_backend<MinBits, MaxBits, unsigned_magnitude, Checked, void> t(b);
      return *a.limbs() > *t.limbs();
   }
   else
   {
      return *a.limbs() > static_cast<ui_type>(b);
   }
}

#ifdef BOOST_MSVC
#pragma warning(pop)
#endif

<<<<<<< HEAD
}}} // namespaces
=======
}}} // namespace boost::multiprecision::backends
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif

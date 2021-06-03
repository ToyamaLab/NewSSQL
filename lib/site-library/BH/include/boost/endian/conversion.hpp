//  boost/endian/conversion.hpp  -------------------------------------------------------//

//  Copyright Beman Dawes 2010, 2011, 2014

//  Distributed under the Boost Software License, Version 1.0.
//  http://www.boost.org/LICENSE_1_0.txt

#ifndef BOOST_ENDIAN_CONVERSION_HPP
#define BOOST_ENDIAN_CONVERSION_HPP

<<<<<<< HEAD
#include <boost/config.hpp>
#include <boost/predef/detail/endian_compat.h>
#include <boost/cstdint.hpp>
#include <boost/endian/detail/intrinsic.hpp>
#include <boost/core/scoped_enum.hpp>
#include <boost/static_assert.hpp>
#include <algorithm>
#include <cstring>  // for memcpy
=======
#include <boost/endian/detail/endian_reverse.hpp>
#include <boost/endian/detail/endian_load.hpp>
#include <boost/endian/detail/endian_store.hpp>
#include <boost/endian/detail/order.hpp>
#include <boost/type_traits/is_class.hpp>
#include <boost/type_traits/is_integral.hpp>
#include <boost/type_traits/is_same.hpp>
#include <boost/type_traits/integral_constant.hpp>
#include <boost/predef/other/endian.h>
#include <boost/static_assert.hpp>
#include <boost/cstdint.hpp>
#include <boost/config.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

//------------------------------------- synopsis ---------------------------------------//

namespace boost
{
namespace endian
{
<<<<<<< HEAD
  BOOST_SCOPED_ENUM_START(order)
  {
    big, little,
# ifdef  BOOST_BIG_ENDIAN
      native = big
# else
      native = little
# endif
  }; BOOST_SCOPED_ENUM_END
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

//--------------------------------------------------------------------------------------//
//                                                                                      //
//                             return-by-value interfaces                               //
//                             suggested by Phil Endecott                               //
//                                                                                      //
//                             user-defined types (UDTs)                                //
//                                                                                      //
//  All return-by-value conversion function templates are required to be implemented in //
//  terms of an unqualified call to "endian_reverse(x)", a function returning the       //
//  value of x with endianness reversed. This provides a customization point for any    //
//  UDT that provides a "endian_reverse" free-function meeting the requirements.        //
//  It must be defined in the same namespace as the UDT itself so that it will be found //
//  by argument dependent lookup (ADL).                                                 //
//                                                                                      //
//--------------------------------------------------------------------------------------//
<<<<<<< HEAD
  
  //  customization for exact-length arithmetic types. See doc/conversion.html/#FAQ.
  //  Note: The omission of a overloads for the arithmetic type (typically long, or
  //  long long) not assigned to one of the exact length typedefs is a deliberate
  //  design decision. Such overloads would be non-portable and thus error prone.
     
  inline int8_t   endian_reverse(int8_t x) BOOST_NOEXCEPT;
  inline int16_t  endian_reverse(int16_t x) BOOST_NOEXCEPT;
  inline int32_t  endian_reverse(int32_t x) BOOST_NOEXCEPT;
  inline int64_t  endian_reverse(int64_t x) BOOST_NOEXCEPT;
  inline uint8_t  endian_reverse(uint8_t x) BOOST_NOEXCEPT;
  inline uint16_t endian_reverse(uint16_t x) BOOST_NOEXCEPT;
  inline uint32_t endian_reverse(uint32_t x) BOOST_NOEXCEPT;
  inline uint64_t endian_reverse(uint64_t x) BOOST_NOEXCEPT;

  //  reverse byte order unless native endianness is big
  template <class EndianReversible >
    inline EndianReversible  big_to_native(EndianReversible  x) BOOST_NOEXCEPT;
    //  Returns: x if native endian order is big, otherwise endian_reverse(x)
  template <class EndianReversible >
    inline EndianReversible  native_to_big(EndianReversible  x) BOOST_NOEXCEPT;
=======

  //  reverse byte order
  //  requires T to be a non-bool integral type
  //  in detail/endian_reverse.hpp
  template<class T> inline BOOST_CONSTEXPR T endian_reverse( T x ) BOOST_NOEXCEPT;

  //  reverse byte order unless native endianness is big
  template <class EndianReversible >
    inline BOOST_CONSTEXPR EndianReversible big_to_native(EndianReversible x) BOOST_NOEXCEPT;
    //  Returns: x if native endian order is big, otherwise endian_reverse(x)
  template <class EndianReversible >
    inline BOOST_CONSTEXPR EndianReversible native_to_big(EndianReversible x) BOOST_NOEXCEPT;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    //  Returns: x if native endian order is big, otherwise endian_reverse(x)

  //  reverse byte order unless native endianness is little
  template <class EndianReversible >
<<<<<<< HEAD
    inline EndianReversible  little_to_native(EndianReversible  x) BOOST_NOEXCEPT;
    //  Returns: x if native endian order is little, otherwise endian_reverse(x)
  template <class EndianReversible >
    inline EndianReversible  native_to_little(EndianReversible  x) BOOST_NOEXCEPT;
=======
    inline BOOST_CONSTEXPR EndianReversible little_to_native(EndianReversible x) BOOST_NOEXCEPT;
    //  Returns: x if native endian order is little, otherwise endian_reverse(x)
  template <class EndianReversible >
    inline BOOST_CONSTEXPR EndianReversible native_to_little(EndianReversible x) BOOST_NOEXCEPT;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    //  Returns: x if native endian order is little, otherwise endian_reverse(x)

  //  generic conditional reverse byte order
  template <BOOST_SCOPED_ENUM(order) From, BOOST_SCOPED_ENUM(order) To,
    class EndianReversible>
<<<<<<< HEAD
      inline EndianReversible  conditional_reverse(EndianReversible from) BOOST_NOEXCEPT;
=======
      inline BOOST_CONSTEXPR EndianReversible conditional_reverse(EndianReversible from) BOOST_NOEXCEPT;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    //  Returns: If From == To have different values, from.
    //           Otherwise endian_reverse(from).
    //  Remarks: The From == To test, and as a consequence which form the return takes, is
    //           is determined at compile time.

  //  runtime conditional reverse byte order
  template <class EndianReversible >
<<<<<<< HEAD
    inline EndianReversible  conditional_reverse(EndianReversible from,
=======
    inline BOOST_CONSTEXPR EndianReversible conditional_reverse(EndianReversible from,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      BOOST_SCOPED_ENUM(order) from_order, BOOST_SCOPED_ENUM(order) to_order)
        BOOST_NOEXCEPT;
      //  Returns: from_order == to_order ? from : endian_reverse(from).

  //------------------------------------------------------------------------------------//


  //  Q: What happened to bswap, htobe, and the other synonym functions based on names
  //     popularized by BSD, OS X, and Linux?
  //  A: Turned out these may be implemented as macros on some systems. Ditto POSIX names
  //     for such functionality. Since macros would cause endless problems with functions
  //     of the same names, and these functions are just synonyms anyhow, they have been
  //     removed.


  //------------------------------------------------------------------------------------//
  //                                                                                    //
  //                            reverse in place interfaces                             //
  //                                                                                    //
  //                             user-defined types (UDTs)                              //
  //                                                                                    //
<<<<<<< HEAD
  //  All reverse in place function templates are required to be implemented in terms   // 
=======
  //  All reverse in place function templates are required to be implemented in terms   //
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  //  of an unqualified call to "endian_reverse_inplace(x)", a function reversing       //
  //  the endianness of x, which is a non-const reference. This provides a              //
  //  customization point for any UDT that provides a "reverse_inplace" free-function   //
  //  meeting the requirements. The free-function must be declared in the same          //
  //  namespace as the UDT itself so that it will be found by argument-dependent        //
  //   lookup (ADL).                                                                    //
  //                                                                                    //
  //------------------------------------------------------------------------------------//

  //  reverse in place
<<<<<<< HEAD
=======
  //  in detail/endian_reverse.hpp
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  template <class EndianReversible>
    inline void endian_reverse_inplace(EndianReversible& x) BOOST_NOEXCEPT;
    //  Effects: x = endian_reverse(x)

  //  reverse in place unless native endianness is big
  template <class EndianReversibleInplace>
    inline void big_to_native_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT;
    //  Effects: none if native byte-order is big, otherwise endian_reverse_inplace(x)
  template <class EndianReversibleInplace>
    inline void native_to_big_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT;
    //  Effects: none if native byte-order is big, otherwise endian_reverse_inplace(x)

  //  reverse in place unless native endianness is little
  template <class EndianReversibleInplace>
    inline void little_to_native_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT;
    //  Effects: none if native byte-order is little, otherwise endian_reverse_inplace(x);
  template <class EndianReversibleInplace>
    inline void native_to_little_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT;
    //  Effects: none if native byte-order is little, otherwise endian_reverse_inplace(x);

  //  generic conditional reverse in place
  template <BOOST_SCOPED_ENUM(order) From, BOOST_SCOPED_ENUM(order) To,
    class EndianReversibleInplace>
<<<<<<< HEAD
  inline void conditional_reverse_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT; 
=======
  inline void conditional_reverse_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

  //  runtime reverse in place
  template <class EndianReversibleInplace>
  inline void conditional_reverse_inplace(EndianReversibleInplace& x,
    BOOST_SCOPED_ENUM(order) from_order,  BOOST_SCOPED_ENUM(order) to_order)
    BOOST_NOEXCEPT;

//----------------------------------- end synopsis -------------------------------------//

<<<<<<< HEAD
  namespace detail
  {
    //  generic reverse function template implementation approach using std::reverse
    //  suggested by Mathias Gaunard. Primary motivation for inclusion is to have an
    //  independent implementation to test against.

    template <class T>
    inline T std_endian_reverse(T x) BOOST_NOEXCEPT
    {
      T tmp(x);
      std::reverse(
        reinterpret_cast<unsigned char*>(&tmp),
        reinterpret_cast<unsigned char*>(&tmp) + sizeof(T));
      return tmp;
    }

    //  conditional unaligned reverse copy, patterned after std::reverse_copy
    template <class T>
      inline void big_reverse_copy(T from, char* to) BOOST_NOEXCEPT;
    template <class T>
      inline void big_reverse_copy(const char* from, T& to) BOOST_NOEXCEPT;
    template <class T>
      inline void little_reverse_copy(T from, char* to) BOOST_NOEXCEPT;
    template <class T>
      inline void little_reverse_copy(const char* from, T& to) BOOST_NOEXCEPT;
  }  // namespace detail

//--------------------------------------------------------------------------------------//
//                                                                                      //
//                            return-by-value implementation                            //
//                                                                                      //
//    -- portable approach suggested by tymofey, with avoidance of undefined behavior   //
//       as suggested by Giovanni Piero Deretta, with a further refinement suggested    //
//       by Pyry Jahkola.                                                               //
//    -- intrinsic approach suggested by reviewers, and by David Stone, who provided    //
//       his Boost licensed macro implementation (detail/intrinsic.hpp)                 //
//                                                                                      //
//--------------------------------------------------------------------------------------//

  inline int8_t endian_reverse(int8_t x) BOOST_NOEXCEPT
  {
    return x;
  }
                                                
  inline int16_t endian_reverse(int16_t x) BOOST_NOEXCEPT
  {
# ifdef BOOST_ENDIAN_NO_INTRINSICS  
    return (static_cast<uint16_t>(x) << 8)
      | (static_cast<uint16_t>(x) >> 8);
# else
    return BOOST_ENDIAN_INTRINSIC_BYTE_SWAP_2(static_cast<uint16_t>(x));
# endif
  }

  inline int32_t endian_reverse(int32_t x) BOOST_NOEXCEPT
  {
# ifdef BOOST_ENDIAN_NO_INTRINSICS  
    uint32_t step16;
    step16 = static_cast<uint32_t>(x) << 16 | static_cast<uint32_t>(x) >> 16;
    return
        ((static_cast<uint32_t>(step16) << 8) & 0xff00ff00)
      | ((static_cast<uint32_t>(step16) >> 8) & 0x00ff00ff);
# else
    return BOOST_ENDIAN_INTRINSIC_BYTE_SWAP_4(static_cast<uint32_t>(x));
# endif
  }

  inline int64_t endian_reverse(int64_t x) BOOST_NOEXCEPT
  {
# ifdef BOOST_ENDIAN_NO_INTRINSICS  
    uint64_t step32, step16;
    step32 = static_cast<uint64_t>(x) << 32 | static_cast<uint64_t>(x) >> 32;
    step16 = (step32 & 0x0000FFFF0000FFFFULL) << 16
           | (step32 & 0xFFFF0000FFFF0000ULL) >> 16;
    return static_cast<int64_t>((step16 & 0x00FF00FF00FF00FFULL) << 8
           | (step16 & 0xFF00FF00FF00FF00ULL) >> 8);
# else
    return BOOST_ENDIAN_INTRINSIC_BYTE_SWAP_8(static_cast<uint64_t>(x));
# endif
  }
  
  inline uint8_t endian_reverse(uint8_t x) BOOST_NOEXCEPT
  {
    return x;
  }

  inline uint16_t endian_reverse(uint16_t x) BOOST_NOEXCEPT
  {
# ifdef BOOST_ENDIAN_NO_INTRINSICS  
    return (x << 8)
      | (x >> 8);
# else
    return BOOST_ENDIAN_INTRINSIC_BYTE_SWAP_2(x);
# endif
  }

  inline uint32_t endian_reverse(uint32_t x) BOOST_NOEXCEPT                           
  {
# ifdef BOOST_ENDIAN_NO_INTRINSICS  
    uint32_t step16;
    step16 = x << 16 | x >> 16;
    return
        ((step16 << 8) & 0xff00ff00)
      | ((step16 >> 8) & 0x00ff00ff);
# else
    return BOOST_ENDIAN_INTRINSIC_BYTE_SWAP_4(x);
# endif
  }

  inline uint64_t endian_reverse(uint64_t x) BOOST_NOEXCEPT
  {
# ifdef BOOST_ENDIAN_NO_INTRINSICS  
    uint64_t step32, step16;
    step32 = x << 32 | x >> 32;
    step16 = (step32 & 0x0000FFFF0000FFFFULL) << 16
           | (step32 & 0xFFFF0000FFFF0000ULL) >> 16;
    return (step16 & 0x00FF00FF00FF00FFULL) << 8
           | (step16 & 0xFF00FF00FF00FF00ULL) >> 8;
# else
    return BOOST_ENDIAN_INTRINSIC_BYTE_SWAP_8(x);
# endif
  }

  template <class EndianReversible >
  inline EndianReversible  big_to_native(EndianReversible  x) BOOST_NOEXCEPT
  {
#   ifdef BOOST_BIG_ENDIAN
    return x;
#   else
    return endian_reverse(x);
#   endif
  }

  template <class EndianReversible >
  inline EndianReversible  native_to_big(EndianReversible  x) BOOST_NOEXCEPT
  {
#   ifdef BOOST_BIG_ENDIAN
    return x;
#   else
    return endian_reverse(x);
#   endif
  }

  template <class EndianReversible >
  inline EndianReversible  little_to_native(EndianReversible  x) BOOST_NOEXCEPT
  {
#   ifdef BOOST_LITTLE_ENDIAN
    return x;
#   else
    return endian_reverse(x);
#   endif
  }

  template <class EndianReversible >
  inline EndianReversible  native_to_little(EndianReversible  x) BOOST_NOEXCEPT
  {
#   ifdef BOOST_LITTLE_ENDIAN
    return x;
#   else
    return endian_reverse(x);
#   endif
  }

  namespace detail
  {
    //  Primary template and specializations to support endian_reverse().
    //  See rationale in endian_reverse() below.
    template <BOOST_SCOPED_ENUM(order) From, BOOST_SCOPED_ENUM(order) To,
        class EndianReversible>
      class value_converter ;  // primary template
    template <class T> class value_converter <order::big, order::big, T>
      {public: T operator()(T x) BOOST_NOEXCEPT {return x;}};
    template <class T> class value_converter <order::little, order::little, T>
      {public: T operator()(T x) BOOST_NOEXCEPT {return x;}};
    template <class T> class value_converter <order::big, order::little, T>
      {public: T operator()(T x) BOOST_NOEXCEPT {return endian_reverse(x);}};
    template <class T> class value_converter <order::little, order::big, T>
      {public: T operator()(T x) BOOST_NOEXCEPT {return endian_reverse(x);}};
  }

  //  generic conditional reverse
  template <BOOST_SCOPED_ENUM(order) From, BOOST_SCOPED_ENUM(order) To,
    class EndianReversible>
  inline EndianReversible  conditional_reverse(EndianReversible from) BOOST_NOEXCEPT  {
    //  work around lack of function template partial specialization by instantiating
    //  a function object of a class that is partially specialized on the two order
    //  template parameters, and then calling its operator().
    detail::value_converter <From, To, EndianReversible> tmp;
    return tmp(from);
  }

  //  runtime conditional reverse
  template <class EndianReversible >
  inline EndianReversible  conditional_reverse(EndianReversible  from,
    BOOST_SCOPED_ENUM(order) from_order, BOOST_SCOPED_ENUM(order) to_order) BOOST_NOEXCEPT
  {
    return from_order == to_order ? from : endian_reverse(from);
  }
=======
namespace detail
{

template<class T> struct is_endian_reversible: boost::integral_constant<bool,
    boost::is_class<T>::value || ( boost::is_integral<T>::value && !boost::is_same<T, bool>::value )>
{
};

} // namespace detail

template <class EndianReversible>
inline BOOST_CONSTEXPR EndianReversible big_to_native( EndianReversible x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible<EndianReversible>::value );

#if BOOST_ENDIAN_BIG_BYTE

    return x;

#else

    return endian_reverse(x);

#endif
  }

template <class EndianReversible>
inline BOOST_CONSTEXPR EndianReversible native_to_big( EndianReversible x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible<EndianReversible>::value );

#if BOOST_ENDIAN_BIG_BYTE

    return x;

#else

    return endian_reverse(x);

#endif
}

template <class EndianReversible>
inline BOOST_CONSTEXPR EndianReversible little_to_native( EndianReversible x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible<EndianReversible>::value );

#if BOOST_ENDIAN_LITTLE_BYTE

    return x;

#else

    return endian_reverse(x);

#endif
}

template <class EndianReversible>
inline BOOST_CONSTEXPR EndianReversible native_to_little( EndianReversible x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible<EndianReversible>::value );

#if BOOST_ENDIAN_LITTLE_BYTE

    return x;

#else

    return endian_reverse(x);

#endif
}

namespace detail
{

template<class EndianReversible>
inline BOOST_CONSTEXPR EndianReversible conditional_reverse_impl( EndianReversible x, boost::true_type ) BOOST_NOEXCEPT
{
    return x;
}

template<class EndianReversible>
inline BOOST_CONSTEXPR EndianReversible conditional_reverse_impl( EndianReversible x, boost::false_type ) BOOST_NOEXCEPT
{
    return endian_reverse( x );
}

} // namespace detail

// generic conditional reverse
template <BOOST_SCOPED_ENUM(order) From, BOOST_SCOPED_ENUM(order) To, class EndianReversible>
inline BOOST_CONSTEXPR EndianReversible conditional_reverse( EndianReversible x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible<EndianReversible>::value );
    return detail::conditional_reverse_impl( x, boost::integral_constant<bool, From == To>() );
}

// runtime conditional reverse
template <class EndianReversible>
inline BOOST_CONSTEXPR EndianReversible conditional_reverse( EndianReversible x,
    BOOST_SCOPED_ENUM(order) from_order, BOOST_SCOPED_ENUM(order) to_order ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible<EndianReversible>::value );
    return from_order == to_order? x: endian_reverse( x );
}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

//--------------------------------------------------------------------------------------//
//                           reverse-in-place implementation                            //
//--------------------------------------------------------------------------------------//

<<<<<<< HEAD
  //  reverse in place
  template <class EndianReversible>
  inline void endian_reverse_inplace(EndianReversible& x) BOOST_NOEXCEPT
  {
    x = endian_reverse(x);
  }

  template <class EndianReversibleInplace>
#   ifdef BOOST_BIG_ENDIAN
  inline void big_to_native_inplace(EndianReversibleInplace&) BOOST_NOEXCEPT {}
#   else
  inline void big_to_native_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT
    { endian_reverse_inplace(x); }
#   endif
  template <class EndianReversibleInplace>
#   ifdef BOOST_BIG_ENDIAN
  inline void native_to_big_inplace(EndianReversibleInplace&) BOOST_NOEXCEPT {}
#   else
  inline void native_to_big_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT
  {
    endian_reverse_inplace(x);
  }
#   endif

  template <class EndianReversibleInplace>
#   ifdef BOOST_LITTLE_ENDIAN
  inline void little_to_native_inplace(EndianReversibleInplace&) BOOST_NOEXCEPT {}
#   else
  inline void little_to_native_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT
    { endian_reverse_inplace(x); }
#   endif
  template <class EndianReversibleInplace>
#   ifdef BOOST_LITTLE_ENDIAN
  inline void native_to_little_inplace(EndianReversibleInplace&) BOOST_NOEXCEPT {}
#   else
  inline void native_to_little_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT
  {
    endian_reverse_inplace(x);
  }
#   endif

  namespace detail
  {
    //  Primary template and specializations support generic 
    //  endian_reverse_inplace().
    //  See rationale in endian_reverse_inplace() below.
    template <BOOST_SCOPED_ENUM(order) From, BOOST_SCOPED_ENUM(order) To,
        class EndianReversibleInplace>
      class converter;  // primary template
    template <class T> class converter<order::big, order::big, T>
      {public: void operator()(T&) BOOST_NOEXCEPT {/*no effect*/}};
    template <class T> class converter<order::little, order::little, T>
      {public: void operator()(T&) BOOST_NOEXCEPT {/*no effect*/}};
    template <class T> class converter<order::big, order::little, T>
      {public: void operator()(T& x) BOOST_NOEXCEPT { endian_reverse_inplace(x); }};
    template <class T> class converter<order::little, order::big, T>
      {public: void operator()(T& x) BOOST_NOEXCEPT { endian_reverse_inplace(x); }};
  }  // namespace detail

  //  generic conditional reverse in place
  template <BOOST_SCOPED_ENUM(order) From, BOOST_SCOPED_ENUM(order) To,
    class EndianReversibleInplace>
  inline void conditional_reverse_inplace(EndianReversibleInplace& x) BOOST_NOEXCEPT
  {
    //  work around lack of function template partial specialization by instantiating
    //  a function object of a class that is partially specialized on the two order
    //  template parameters, and then calling its operator().
    detail::converter<From, To, EndianReversibleInplace> tmp;
    tmp(x);  // call operator ()
  }

  //  runtime reverse in place
  template <class EndianReversibleInplace>
  inline void conditional_reverse_inplace(EndianReversibleInplace& x,
    BOOST_SCOPED_ENUM(order) from_order,  BOOST_SCOPED_ENUM(order) to_order)
    BOOST_NOEXCEPT
  {
    if (from_order != to_order)
      endian_reverse_inplace(x);
  }


  namespace detail
  {
    template <class T>
    inline void big_reverse_copy(T from, char* to) BOOST_NOEXCEPT
    {
#     ifdef BOOST_BIG_ENDIAN
      std::memcpy(to, reinterpret_cast<const char*>(&from), sizeof(T));
#     else
      std::reverse_copy(reinterpret_cast<const char*>(&from),
        reinterpret_cast<const char*>(&from) + sizeof(T), to);
#     endif
    }
    template <class T>
    inline void big_reverse_copy(const char* from, T& to) BOOST_NOEXCEPT
    {
#     ifdef BOOST_BIG_ENDIAN
      std::memcpy(reinterpret_cast<char*>(&to), from, sizeof(T));
#     else
      std::reverse_copy(from, from + sizeof(T), reinterpret_cast<char*>(&to));
#     endif
    }
    template <class T>
    inline void little_reverse_copy(T from, char* to) BOOST_NOEXCEPT
    {
#     ifdef BOOST_LITTLE_ENDIAN
      std::memcpy(to, reinterpret_cast<const char*>(&from), sizeof(T));
#     else
      std::reverse_copy(reinterpret_cast<const char*>(&from),
        reinterpret_cast<const char*>(&from) + sizeof(T), to);
#     endif
    }
    template <class T>
    inline void little_reverse_copy(const char* from, T& to) BOOST_NOEXCEPT
    {
#     ifdef BOOST_LITTLE_ENDIAN
      std::memcpy(reinterpret_cast<char*>(&to), from, sizeof(T));
#     else
      std::reverse_copy(from, from + sizeof(T), reinterpret_cast<char*>(&to));
#     endif
    }
  }  // namespace detail
=======
namespace detail
{

template<class T> struct is_endian_reversible_inplace: boost::integral_constant<bool,
    boost::is_class<T>::value || ( boost::is_integral<T>::value && !boost::is_same<T, bool>::value )>
{
};

} // namespace detail

#if BOOST_ENDIAN_BIG_BYTE

template <class EndianReversibleInplace>
inline void big_to_native_inplace( EndianReversibleInplace& ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
}

#else

template <class EndianReversibleInplace>
inline void big_to_native_inplace( EndianReversibleInplace& x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
    endian_reverse_inplace( x );
}

#endif

#if BOOST_ENDIAN_BIG_BYTE

template <class EndianReversibleInplace>
inline void native_to_big_inplace( EndianReversibleInplace& ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
}

#else

template <class EndianReversibleInplace>
inline void native_to_big_inplace( EndianReversibleInplace& x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
    endian_reverse_inplace( x );
}

#endif

#if BOOST_ENDIAN_LITTLE_BYTE

template <class EndianReversibleInplace>
inline void little_to_native_inplace( EndianReversibleInplace& ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
}

#else

template <class EndianReversibleInplace>
inline void little_to_native_inplace( EndianReversibleInplace& x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
    endian_reverse_inplace( x );
}

#endif

#if BOOST_ENDIAN_LITTLE_BYTE

template <class EndianReversibleInplace>
inline void native_to_little_inplace( EndianReversibleInplace& ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
}

#else

template <class EndianReversibleInplace>
inline void native_to_little_inplace( EndianReversibleInplace& x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
    endian_reverse_inplace( x );
}

#endif

namespace detail
{

template<class EndianReversibleInplace>
inline void conditional_reverse_inplace_impl( EndianReversibleInplace&, boost::true_type ) BOOST_NOEXCEPT
{
}

template<class EndianReversibleInplace>
inline void conditional_reverse_inplace_impl( EndianReversibleInplace& x, boost::false_type ) BOOST_NOEXCEPT
{
    endian_reverse_inplace( x );
}

}  // namespace detail

// generic conditional reverse in place
template <BOOST_SCOPED_ENUM(order) From, BOOST_SCOPED_ENUM(order) To, class EndianReversibleInplace>
inline void conditional_reverse_inplace( EndianReversibleInplace& x ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );
    detail::conditional_reverse_inplace_impl( x, boost::integral_constant<bool, From == To>() );
}

// runtime reverse in place
template <class EndianReversibleInplace>
inline void conditional_reverse_inplace( EndianReversibleInplace& x,
    BOOST_SCOPED_ENUM(order) from_order, BOOST_SCOPED_ENUM(order) to_order ) BOOST_NOEXCEPT
{
    BOOST_STATIC_ASSERT( detail::is_endian_reversible_inplace<EndianReversibleInplace>::value );

    if( from_order != to_order )
    {
        endian_reverse_inplace( x );
    }
}

// load/store convenience functions

// load 16

inline boost::int16_t load_little_s16( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int16_t, 2, order::little>( p );
}

inline boost::uint16_t load_little_u16( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint16_t, 2, order::little>( p );
}

inline boost::int16_t load_big_s16( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int16_t, 2, order::big>( p );
}

inline boost::uint16_t load_big_u16( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint16_t, 2, order::big>( p );
}

// load 24

inline boost::int32_t load_little_s24( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int32_t, 3, order::little>( p );
}

inline boost::uint32_t load_little_u24( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint32_t, 3, order::little>( p );
}

inline boost::int32_t load_big_s24( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int32_t, 3, order::big>( p );
}

inline boost::uint32_t load_big_u24( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint32_t, 3, order::big>( p );
}

// load 32

inline boost::int32_t load_little_s32( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int32_t, 4, order::little>( p );
}

inline boost::uint32_t load_little_u32( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint32_t, 4, order::little>( p );
}

inline boost::int32_t load_big_s32( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int32_t, 4, order::big>( p );
}

inline boost::uint32_t load_big_u32( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint32_t, 4, order::big>( p );
}

// load 40

inline boost::int64_t load_little_s40( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int64_t, 5, order::little>( p );
}

inline boost::uint64_t load_little_u40( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint64_t, 5, order::little>( p );
}

inline boost::int64_t load_big_s40( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int64_t, 5, order::big>( p );
}

inline boost::uint64_t load_big_u40( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint64_t, 5, order::big>( p );
}

// load 48

inline boost::int64_t load_little_s48( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int64_t, 6, order::little>( p );
}

inline boost::uint64_t load_little_u48( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint64_t, 6, order::little>( p );
}

inline boost::int64_t load_big_s48( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int64_t, 6, order::big>( p );
}

inline boost::uint64_t load_big_u48( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint64_t, 6, order::big>( p );
}

// load 56

inline boost::int64_t load_little_s56( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int64_t, 7, order::little>( p );
}

inline boost::uint64_t load_little_u56( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint64_t, 7, order::little>( p );
}

inline boost::int64_t load_big_s56( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int64_t, 7, order::big>( p );
}

inline boost::uint64_t load_big_u56( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint64_t, 7, order::big>( p );
}

// load 64

inline boost::int64_t load_little_s64( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int64_t, 8, order::little>( p );
}

inline boost::uint64_t load_little_u64( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint64_t, 8, order::little>( p );
}

inline boost::int64_t load_big_s64( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::int64_t, 8, order::big>( p );
}

inline boost::uint64_t load_big_u64( unsigned char const * p ) BOOST_NOEXCEPT
{
    return boost::endian::endian_load<boost::uint64_t, 8, order::big>( p );
}

// store 16

inline void store_little_s16( unsigned char * p, boost::int16_t v )
{
    boost::endian::endian_store<boost::int16_t, 2, order::little>( p, v );
}

inline void store_little_u16( unsigned char * p, boost::uint16_t v )
{
    boost::endian::endian_store<boost::uint16_t, 2, order::little>( p, v );
}

inline void store_big_s16( unsigned char * p, boost::int16_t v )
{
    boost::endian::endian_store<boost::int16_t, 2, order::big>( p, v );
}

inline void store_big_u16( unsigned char * p, boost::uint16_t v )
{
    boost::endian::endian_store<boost::uint16_t, 2, order::big>( p, v );
}

// store 24

inline void store_little_s24( unsigned char * p, boost::int32_t v )
{
    boost::endian::endian_store<boost::int32_t, 3, order::little>( p, v );
}

inline void store_little_u24( unsigned char * p, boost::uint32_t v )
{
    boost::endian::endian_store<boost::uint32_t, 3, order::little>( p, v );
}

inline void store_big_s24( unsigned char * p, boost::int32_t v )
{
    boost::endian::endian_store<boost::int32_t, 3, order::big>( p, v );
}

inline void store_big_u24( unsigned char * p, boost::uint32_t v )
{
    boost::endian::endian_store<boost::uint32_t, 3, order::big>( p, v );
}

// store 32

inline void store_little_s32( unsigned char * p, boost::int32_t v )
{
    boost::endian::endian_store<boost::int32_t, 4, order::little>( p, v );
}

inline void store_little_u32( unsigned char * p, boost::uint32_t v )
{
    boost::endian::endian_store<boost::uint32_t, 4, order::little>( p, v );
}

inline void store_big_s32( unsigned char * p, boost::int32_t v )
{
    boost::endian::endian_store<boost::int32_t, 4, order::big>( p, v );
}

inline void store_big_u32( unsigned char * p, boost::uint32_t v )
{
    boost::endian::endian_store<boost::uint32_t, 4, order::big>( p, v );
}

// store 40

inline void store_little_s40( unsigned char * p, boost::int64_t v )
{
    boost::endian::endian_store<boost::int64_t, 5, order::little>( p, v );
}

inline void store_little_u40( unsigned char * p, boost::uint64_t v )
{
    boost::endian::endian_store<boost::uint64_t, 5, order::little>( p, v );
}

inline void store_big_s40( unsigned char * p, boost::int64_t v )
{
    boost::endian::endian_store<boost::int64_t, 5, order::big>( p, v );
}

inline void store_big_u40( unsigned char * p, boost::uint64_t v )
{
    boost::endian::endian_store<boost::uint64_t, 5, order::big>( p, v );
}

// store 48

inline void store_little_s48( unsigned char * p, boost::int64_t v )
{
    boost::endian::endian_store<boost::int64_t, 6, order::little>( p, v );
}

inline void store_little_u48( unsigned char * p, boost::uint64_t v )
{
    boost::endian::endian_store<boost::uint64_t, 6, order::little>( p, v );
}

inline void store_big_s48( unsigned char * p, boost::int64_t v )
{
    boost::endian::endian_store<boost::int64_t, 6, order::big>( p, v );
}

inline void store_big_u48( unsigned char * p, boost::uint64_t v )
{
    boost::endian::endian_store<boost::uint64_t, 6, order::big>( p, v );
}

// store 56

inline void store_little_s56( unsigned char * p, boost::int64_t v )
{
    boost::endian::endian_store<boost::int64_t, 7, order::little>( p, v );
}

inline void store_little_u56( unsigned char * p, boost::uint64_t v )
{
    boost::endian::endian_store<boost::uint64_t, 7, order::little>( p, v );
}

inline void store_big_s56( unsigned char * p, boost::int64_t v )
{
    boost::endian::endian_store<boost::int64_t, 7, order::big>( p, v );
}

inline void store_big_u56( unsigned char * p, boost::uint64_t v )
{
    boost::endian::endian_store<boost::uint64_t, 7, order::big>( p, v );
}

// store 64

inline void store_little_s64( unsigned char * p, boost::int64_t v )
{
    boost::endian::endian_store<boost::int64_t, 8, order::little>( p, v );
}

inline void store_little_u64( unsigned char * p, boost::uint64_t v )
{
    boost::endian::endian_store<boost::uint64_t, 8, order::little>( p, v );
}

inline void store_big_s64( unsigned char * p, boost::int64_t v )
{
    boost::endian::endian_store<boost::int64_t, 8, order::big>( p, v );
}

inline void store_big_u64( unsigned char * p, boost::uint64_t v )
{
    boost::endian::endian_store<boost::uint64_t, 8, order::big>( p, v );
}

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}  // namespace endian
}  // namespace boost

#endif // BOOST_ENDIAN_CONVERSION_HPP
